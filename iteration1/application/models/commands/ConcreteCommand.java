package application.models.commands;

import application.models.playerAsset.Player;
import application.models.tileState.AssetOccupance;
import application.models.tileState.Map;
import application.models.tileState.Occupance;

class concreteCommand implements Command {
    protected int turnsToExcecute; //Can be private with final set methods?
    protected int turnsRemaining;
    private Map map;
    private Player player;
    private String commandType;

    concreteCommand(Player _p, Map _m){
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

class newStructureCommand extends concreteCommand{
    private String assetID;

    newStructureCommand(Player _p, Map _m){
        super(_p, _m);
    }

    public void doInitialize(String ... strings){
        assetID = strings[1];
    }

    @Override
    public void execute() {
        Map map = getMap();
        Player player = getPlayer();
        if(player.canCreateStructure(assetID)){
            System.out.println("Creating Occupance...\n");
            Occupance _o = new AssetOccupance(player.createStructure(assetID));
            System.out.println("new asset occupance with id = " + assetID + "\n");
            map.getTileState(_o.getTileID()).addOccupance(_o);
        }
    }
}

class moveAssetCommand extends concreteCommand{
    private String destinationTileID;
    private String assetID;

    moveAssetCommand(Player _p, Map _m){
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
    }
}

class nullCommand extends concreteCommand{
    nullCommand(Player _p, Map _m){
        super(_p, _m);
    }
}