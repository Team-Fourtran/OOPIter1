package application.models.playerAsset;

import application.models.commands.Command;

import java.util.*;

public class Army extends PlayerAsset{
    
    ArrayList<Unit> battleGroup = new ArrayList<Unit>();
    ArrayList<Unit> reinforcements = new ArrayList<Unit>();
    String rallyPoint;
    Queue<Command> commandQueue = new LinkedList<>();
    int commandCount;

    public Army(ArrayList<Unit> units, String rallyPoint){
        commandCount = 0;
        this.rallyPoint = rallyPoint;
        for (Unit u: units){
            if (u.getLocation().equals(rallyPoint)) {
                battleGroup.add(u);
            }
            else {
                reinforcements.add(u);
            }
        }
    }

    //method to set new rally point. If any reinforcements are on that tile
    //put them in the battle group
    public void setRallyPoint(String location){
        rallyPoint = location;
        for (Unit u: reinforcements)
            if (u.getLocation() == rallyPoint){
                battleGroup.add(u);
                reinforcements.remove(u);
            }
    }

    //return all units in the army
    public ArrayList<Unit> getUnits(){
        ArrayList<Unit> newList = battleGroup;
        newList.addAll(reinforcements);
        return newList;
    }

    //method to check if an army has a colonist to make a structure
    public boolean hasColonist(){
        for (Unit i: battleGroup)
            if (i instanceof Colonist)
                return true;
        return false;
    }

    //after a structure is made, remove the colonist from the army
    public void removeColonist(){
    	Unit removed = null;
        for (Unit i: battleGroup) {
            if (i instanceof Colonist)
            	removed = i;
        }
        battleGroup.remove(removed);
    }

    //see if any reinforcements arrived at the rally point
    //to be called each turn
    public void updateArmyTypes(){
        for (Unit u: reinforcements)
            if (u.getLocation() == rallyPoint){
                battleGroup.add(u);
                reinforcements.remove(u);
            }
    }

    //execute appropriate number of commands for this turn
    //if movement, could be many commands
    //if multi-turn command, stall until turn count is reached
    public void executeCommand(){

        double turns = commandQueue.peek().getTurns();

        if (turns != 0) {
            commandCount++;
            if (equal(commandQueue.peek().getTurns(), commandCount)) {
                commandQueue.peek().execute();
                commandQueue.remove();
                commandCount = 0;
            }
        }
        else{
            int numCommands = 0;
            double turnCount = 0;
            for (Command c: commandQueue){
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

    //helper method for execute
    public boolean equal(double d, int i){
        double n = d-i;
        if (n < 0.000001)
            return true;
        return false;
    }
    

}
