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
    protected int moveCounter = 0;

    public void setID(String id){
        assetID = id;
    }

    public String getID(){
        return assetID;
    }

    public String getLocation(){
        return locationID;
    }
    public int getOffDamage(){
        return offDamage;
    }
    public int getDefDamage(){
        return defDamage;
    }
    public int getArmor(){
        return armor;
    }
    public int getMaxHealth(){
        return maxHealth;
    }
    public int getCurrentHealth(){
        return currentHealth;
    }
    public boolean getPoweredUp(){
        return poweredUp;
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
        }
    }

    public void executeCommand() {
        if (!hasExecutedCommand) {

            int turns = (int) commandQueue.peek().getTurns();

            if (turns != 0) {
                commandCount++;
                if (equal(commandQueue.peek().getTurns(), commandCount)) {
                    commandQueue.remove().execute();
                    commandCount = 0;
                    hasExecutedCommand = true;
                }
            } else {
                int numCommands = 0;
                double turnCount = 0;
                boolean endMovement = false;
                for (Command c : commandQueue) {
                    turnCount += c.getTurns();
                    if (turnCount >= 1) {
                        endMovement = true;
                        break;
                    }
                        numCommands++;
                    }

                    for (int i = 0; i < numCommands; i++) {
                        commandQueue.remove().execute();
                        moveCounter++;
                    }

                    if (moveCounter == 3 || endMovement)
                        hasExecutedCommand = true;

                }
            if(hasExecutedCommand){
                moveCounter = 0;
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

    public void clearQueue(){
        commandQueue.clear();
    }

    public void heal(){}

    public void resetCommands(){
        hasExecutedCommand = false;
    }

    public String getType(){
       return "basic asset type";
    }

}
