package application.models.commands;

import application.models.playerAsset.Player;
import application.models.tileState.AssetOccupance;
import application.models.tileState.Map;
import application.models.tileState.Occupance;
/*
 * Create a new unit through a structure
 */
public class NewUnitCommand extends ConcreteCommand{
    private String assetID;
    private String unitType;

    NewUnitCommand(Player _p, Map _m){
        super(_p, _m);
    }

    // Specify the assetID (structure creating it) and the unitType to create. Notify player of this command
    public void doInitialize(String ... strings){
        assetID = strings[1];
        unitType = strings[2];
        getPlayer().notify(assetID, this);
    }

    @Override
    public void execute() {
        Map map = getMap();
        Player player = getPlayer();
        if(player.canCreateUnit(assetID, unitType)){
            System.out.println("Creating Occupance...\n");
            Occupance _o = new AssetOccupance(player.createUnit(unitType, assetID));
            System.out.println("new asset occupance with id = " + assetID + "\n");
            map.getTileState(_o.getTileID()).addOccupance(_o);
        }
    }

    @Override
    public double getTurns(){
        return 2.0;
    }
}
