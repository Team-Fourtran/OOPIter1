package application.models.commands;

import application.models.playerAsset.Player;
import application.models.tileState.Map;
import java.util.ArrayList;

abstract class ConcreteCommand implements Command {
    protected int turnsToExcecute; //Can be private with final set methods?
    protected int turnsRemaining;
    private Map map;
    private Player player;
    private String commandType;
    protected boolean needsUnpacked = false;

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

