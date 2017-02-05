package application.models.commands;

import java.util.Iterator;

import application.models.playerAsset.Player;
import application.models.playerAsset.Unit;
import application.models.playerAsset.UnitManager;
import application.models.tileInfo.Item;
import application.models.tileState.AssetOccupance;
import application.models.tileState.Map;
import application.models.tileState.Occupance;
import application.models.tileState.TileState;

class ConcreteCommand implements Command {
    protected int turnsToExcecute; //Can be private with final set methods?
    protected int turnsRemaining;
    private Map map;
    private Player player;
    private String commandType;

    ConcreteCommand(Player _p, Map _m){
        System.out.println("Creating " + this.getClass().toString() + "...\n");
        this.map = _m;
        this.player = _p;
    }
    Player getPlayer() {
        return player;
    }
    Map getMap() {
        return map;
    }
    @Override
    public void execute() {
        //Let subclasses override
    }
    @Override
    public void initialize(String... strings) {
        //Template method
        setCommandType(strings[0]); //maybe use enum instead of strings?
        doInitialize(strings);
    }
    protected void setCommandType(String type){
        this.commandType = type;
    }
    protected void doInitialize(String ... strings){
        //Let subclasses override
    }
}

/*
 * Command intended for the initial units (2 explorers, 1 colonist) for a given Player
 */
class InitialUnitsCommand extends ConcreteCommand {
	private String destinationTileID;
	
	InitialUnitsCommand(Player _p, Map _m) {
		super(_p, _m);
	}
	
	public void doInitialize(String... strings) {
		destinationTileID = strings[2];
	}
	
	@Override
	public void execute() {
		UnitManager um = super.getPlayer().getUnitManager();
		Unit unit = um.addNewUnit("colonist", destinationTileID);
		Occupance o = new AssetOccupance(unit);
		o.setTileID(destinationTileID);
		TileState ts = super.getMap().getTileState(destinationTileID);
		ts.addOccupance(o);
	}
}

class NewStructureCommand extends ConcreteCommand{
    private String assetID;

    NewStructureCommand(Player _p, Map _m){
        super(_p, _m);
    }

    public void doInitialize(String ... strings){
        assetID = strings[1];
        System.out.println("AssetID " + assetID);
    }

    @Override
    public void execute() {
        Map map = getMap();
        Player player = getPlayer();
        if(player.canCreateStructure(assetID)){
            System.out.println("Creating Occupance...\n");
            UnitManager um = player.getUnitManager();
            String tileID = new String();
            
            // Based upon the assetID, find the proper Unit to get its locationID (i.e. tileID)
    		Iterator<Unit> i = um.getUnitList().iterator();
    		while (i.hasNext()) {
    			Unit currentUnit = i.next();
    			if (currentUnit.getID() == assetID) {
    				tileID = currentUnit.getLocation();
    			}
    		}
    		
            Occupance _o = new AssetOccupance(player.createStructure(assetID));
            _o.setTileID(tileID);
            System.out.println("new asset occupance with id = " + assetID + "\n");
            map.getTileState(_o.getTileID()).addOccupance(_o);
            
        }
    }
}

class MoveAssetCommand extends ConcreteCommand{
    private String destinationTileID;
    private String assetID;

    MoveAssetCommand(Player _p, Map _m){
        super(_p, _m);
    }
    @Override
    public void doInitialize(String... strings) {
        assetID = strings[1];
        destinationTileID = strings[2];
    }
    @Override
    public void execute() {
        Map map = getMap();
        Player player = getPlayer();
        
        // get the TileState where the asset is located
        TileState ts = map.getTileState(player.getPlayerAsset(assetID).getLocation());
        
        // determine the direction of the desired tile (discrete movement for now)
        // pass in destinationTileID
        ts.moveOccupance(assetID, ts.getNeighborDirection(destinationTileID));
        
        TileState newTS = map.getTileState("T1");
//        System.out.println(newTS.getOccupance().get(0).getClass().getSimpleName());
        // call moveOccupance of the TS
        
        
    }
}

class NullCommand extends ConcreteCommand{
    NullCommand(Player _p, Map _m){
        super(_p, _m);
    }
}