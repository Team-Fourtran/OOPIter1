package application.models.playerAsset;

import application.models.commands.Command;

import java.util.*;

/* Management class for a Player's armies. Keeps references to
   all armies and passes commands to specific ones.
 */
public class ArmyManager {

    ArrayList<Army> armyList;
    final int maxArmies = 10;
    static ArrayList<String> armyIDs = new ArrayList<>();
    
    public ArmyManager(){
        armyList = new ArrayList<>();
        for (int i = 1; i <= 20; i++)
            armyIDs.add("a" + i);
        }

    //create a new army with given units and add it to the armyList
    public Army formArmy(ArrayList<Unit> units, String rallyPoint){
        Army newArmy = new Army(units, rallyPoint);
        newArmy.setID(armyIDs.get(0));
        newArmy.setLocation(rallyPoint);
        armyList.add(newArmy);
        armyIDs.remove(0);
        return newArmy;

    }

    //Given an armyID, return the army if it exists
    public Army findArmy(String armyID){
        for (Army a: armyList){
            if (a.getID().equals(armyID))
                return a;
        }
        return null;
    }
    
    //delete army from armyList and return the units
    public ArrayList<Unit> decommission(String ArmyID){
        ArrayList<Unit> units = new ArrayList<>();
        for (Army army: armyList){
            if (army.getID().equals(ArmyID)){
                units = army.getUnits();
                armyIDs.add(army.getID());
                armyList.remove(army);
            }    
        }
        return units;
    }

    //calculate total upkeep from each army, unit by unit
    public int calculateTotalUpkeep(){
        int totalUpkeep = 0;
        for (Army a: armyList){
            ArrayList<Unit> units = a.getUnits();
            for (Unit u: units)
                totalUpkeep += u.getUpkeep();
        }
        return totalUpkeep;
    }

    //free an army's ID when they are done using it for recycling
    public void freeID(String assetID) {
    	int escapee = Integer.parseInt(assetID.substring(assetID.lastIndexOf("u") + 1).trim());
    	for (int i = 0; i < armyIDs.size(); i++) {
    		String currentID = armyIDs.get(i);
    		int id = Integer.parseInt(currentID.substring(currentID.lastIndexOf("u") + 1).trim());
    		if (escapee < id) {
    			armyIDs.add(i, assetID);
    			break;
    		}
    	}
    }

    //set a specific army's rally point
    public void setRallyPoint(String armyID, String rallyPoint){
        for (Army a: armyList)
            if (a.getID().equals(armyID))
                a.setRallyPoint(rallyPoint);
    }

    //get the position of an army by armyID
    public String getPosition(String assetID){
        for (Army a: armyList)
            if (a.getID().equals(assetID))
                return a.getLocation();
        return null;
    }

    //add command into specific structure's queue
    public void addCommand(Command c, String armyID){
        for (Army a: armyList)
            if (a.getID().equals(armyID))
                a.addCommand(c);
    }

    //assign an individual unit in an army a command to move
    public void addMoveCommand(Command c, String unitID){
        for (Army a: armyList)
            if (a.getUnit(unitID) != null)
                a.getUnit(unitID).addCommand(c);
    }

    //Go through all of the armies and, if possible, execute a command
    public void executeCommands(){
        for (Army a: armyList) {
        	a.updateArmyTypes();
        	
            if (!a.emptyQueue()) {
                a.executeCommand();
            }
        }
    }

    //reset all of the armies' abilities to execute commands
    public void resetCommands(){
        for (Army a: armyList) {
            a.resetCommands();
        }
    }

    //reset an army's units' movement queues
    public void resetArmyUnitQueue(String armyID){
        ArrayList<Unit> units = findArmy(armyID).getUnits();
        for (Unit u: units)
            u.clearQueue();

    }

    public ListIterator makeListIterator(){
        return armyList.listIterator();
    }


   
}
