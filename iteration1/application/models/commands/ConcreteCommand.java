package application.models.commands;

import application.models.playerAsset.Player;
import application.models.tileState.Map;
import java.util.ArrayList;
/*
 * See Command interface. Also provides capabilities for commands to consist of multiple other commands (packing)
 */
abstract class ConcreteCommand implements Command {
    private Map map;
    private Player player;
    private String commandType;
    boolean needsUnpacked = false;

    ConcreteCommand(Player _p, Map _m){
        setPacking();
        this.map = _m;
        this.player = _p;
    }
    
    // Retrieve the Player who issued this command
    Player getPlayer() {
        return player;
    }

    // Retrieve the map reference
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

    // The amount of turns needed for the command to execute
    @Override
    public double getTurns(){
        //Packed commands won't have a meaningful value here, so default:
        return 0;
    }

    /*
     * Set the command type and additional arguments
     * Use template method so that each subclass can override doInitialize()(non-Javadoc)
     * @see application.models.commands.Command#initialize(java.lang.String[])
     */
    @Override
    public void initialize(String... strings) {
        //Template method
        setCommandType(strings[0]); //maybe use enum instead of strings?
        doInitialize(strings);
    }

    // Set the command type
    private void setCommandType(String type){
        this.commandType = type;
    }

    // Initialize command with other arguments
    protected void doInitialize(String ... strings){
        //Let subclasses override
    }

    // Set whether or not this command can be packed. ConcreteCommands are not meaningful, therefore cannot be packed
    protected void setPacking(){
        needsUnpacked = false;
    }

    // See above
    public void unpack() {
        System.out.println("You shouldn't be executing me");
    }
}

