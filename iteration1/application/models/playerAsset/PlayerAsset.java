package application.models.playerAsset;

import application.models.commands.Command;
import java.util.*;

public abstract class PlayerAsset {

    protected int offDamage;
    protected int defDamage;
    protected int armor;
    protected int maxHealth;
    protected int currentHealth;
    protected int upkeep;
    protected boolean poweredUp;
    protected String locationID;
    protected String assetID;
    protected boolean hasExecutedCommand = false;
    protected Queue<Command> commandQueue = new LinkedList<Command>();
    protected int commandCount = 0;


    public void setID(String id){
        assetID = id;
    }

    public String getID(){
        return assetID;
    }

    public String getLocation(){
        return locationID;
    }

    public void setLocation(String location){
        locationID = location;
    }

    public int getUpkeep(){
        return upkeep;
    }

    public void powerUp(){
        if (!poweredUp)         //incorrect logic, will be fixed
            upkeep *= 4;
    }

    public void powerDown(){
        if (poweredUp)
            upkeep = (int)Math.ceil(.25*upkeep);
    }

    public void addCommand(Command c){
        commandQueue.add(c);
        if (!hasExecutedCommand) {
            executeCommand();
            hasExecutedCommand = true;
        }
    }

    public void executeCommand(){
        if (!hasExecutedCommand) {

            int turns = (int)commandQueue.peek().getTurns();

            if (turns != 0) {
                commandCount++;
                if (equal(commandQueue.peek().getTurns(), commandCount)) {
                    commandQueue.peek().execute();
                    commandQueue.remove();
                    commandCount = 0;
                }
            } else {
                int numCommands = 0;
                double turnCount = 0;
                for (Command c : commandQueue) {
                    turnCount += c.getTurns();
                    numCommands++;
                    if (turnCount >= .99)
                        break;
                }

                for (int i = 0; i < numCommands; i++) {
                    commandQueue.peek().execute();
                    commandQueue.remove();
                }

            }
        }
    }

    public boolean equal(double d, int i){
        double n = d-i;
        if (n < 0.000001)
            return true;
        return false;
    }

    public boolean emptyQueue() {
        if (commandQueue.size() == 0)
            return true;
        return false;
    }

    public void resetCommands(){
        hasExecutedCommand = false;
    }

}
