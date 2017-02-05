package application.models.commands;

import application.models.playerAsset.Player;
import application.models.tileState.AssetOccupance;
import application.models.tileState.Directions;
import application.models.tileState.Map;
import application.models.tileState.Occupance;

import java.util.ArrayList;

class concreteCommand implements Command {
    protected int turnsToExcecute; //Can be private with final set methods?
    protected int turnsRemaining;
    private Map map;
    private Player player;
    private String commandType;
    protected boolean needsUnpacked = false;

    concreteCommand(Player _p, Map _m){
        System.out.println("Creating " + this.getClass().toString() + "...\n");
        setPacking();
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
        System.out.println("You shouldn't be executing me");
    }
    @Override
    public void initialize(String... strings) {
        //Template method
        setCommandType(strings[0]); //maybe use enum instead of strings?
        doInitialize(strings);
    }
    @Override
    public boolean needsUnpacked(){
        return needsUnpacked;
    }

    private void setCommandType(String type){
        this.commandType = type;
    }
    protected void doInitialize(String ... strings){
        //Let subclasses override
    }
    protected void setPacking(){
        needsUnpacked = false;
    }

    public ArrayList<Command> unpack() {
        System.out.println("You shouldn't be executing me");
        return null;
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
            map.getTileState(_o.getTileID()).addOccupance(_o).removeOccupance(assetID);
        }
    }
}

class newUnitCommand extends concreteCommand{
    private String assetID;
    private String unitType;

    newUnitCommand(Player _p, Map _m){
        super(_p, _m);
    }

    public void doInitialize(String ... strings){
        assetID = strings[1];
        unitType = strings[2];
    }

    @Override
    public void execute() {
        Map map = getMap();
        Player player = getPlayer();
        if(player.canCreateUnit(assetID)){
            System.out.println("Creating Occupance...\n");
            Occupance _o = new AssetOccupance(player.createUnit(unitType, assetID));
            System.out.println("new asset occupance with id = " + assetID + "\n");
            map.getTileState(_o.getTileID()).addOccupance(_o).removeOccupance(assetID);
        }
    }
}

class moveAssetCommand extends concreteCommand{
    private String startTileID;
    private String destinationTileID;
    private String assetID;


    moveAssetCommand(Player _p, Map _m){
        super(_p, _m);
    }
    @Override
    public void doInitialize(String... strings) {
        if(strings.length != 4){
            System.out.println("Not enough arguments for initialize");
        }
        assetID = strings[1];
        startTileID = strings[2];
        destinationTileID = strings[3];

    }
    @Override
    protected void setPacking(){
        needsUnpacked = true;
    }

    @Override
    public ArrayList<Command> unpack(){
        String path = getMap().generatePath(startTileID, destinationTileID);
        //e.g., N_N_NW_NW_W
        String[] directionsArray = path.split("[_]");
        ArrayList<Command> cmdList = new ArrayList<>(directionsArray.length);
        for(int i = 0; i < directionsArray.length; i++){
            cmdList.add(new moveDirectionCommand(getPlayer(), getMap()));
            cmdList.get(i).initialize("MVD", assetID, directionsArray[i]);
        }
        return cmdList;
    }
}

class moveDirectionCommand extends concreteCommand{
    private Directions direction;
    private String degreesDirection;
    private String assetID;

    moveDirectionCommand(Player _p, Map _m){
        super(_p, _m);
    }

    @Override
    public void doInitialize(String... strings) {
        assetID = strings[1];
        degreesDirection = strings[2];
        //direction = ????
    }
    @Override
    public void execute() {
        Map map = getMap();
        Player player = getPlayer();
        System.out.println("Moving " + assetID + " direction " + direction);
        map.getTileState(player.getPosition(assetID)).moveOccupance(assetID, direction);
    }
    @Override
    protected void setPacking(){
        needsUnpacked = false;
    }
}

class nullCommand extends concreteCommand{
    nullCommand(Player _p, Map _m){
        super(_p, _m);
    }
}