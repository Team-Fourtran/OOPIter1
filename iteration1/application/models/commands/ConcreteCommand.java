package application.models.commands;

import application.models.playerAsset.Player;
import application.models.tileState.Map;
import java.util.ArrayList;

abstract class ConcreteCommand implements Command {
    private Map map;
    private Player player;
    private String commandType;
    boolean needsUnpacked = false;

    ConcreteCommand(Player _p, Map _m){
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

    //Each subclass should write its own execute() method
    @Override
    public void execute() {
        //Let subclasses override
        //Packed commands shouldn't be executed, hence the below message:
        System.out.println("You shouldn't be executing me");
    }

    @Override
    public double getTurns(){
        //Packed commands won't have a meaningful value here, so default:
        return 0;
    }

    //Use template method so that each subclass can override doInitialize()
    @Override
    public void initialize(String... strings) {
        //Template method
        setCommandType(strings[0]); //maybe use enum instead of strings?
        doInitialize(strings);
    }

    //Some commands will need to be "unpacked", thus generating a number of smaller
    //commands, which are then passed on to their respective command queues
    @Override
    public boolean needsUnpacked(){
        return needsUnpacked;   // Value set by subclasses, defaults to false
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

