package application.models.playerAsset;

import application.models.commands.Command;

import java.util.*;

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

    public void setRallyPoint(String armyID, String rallyPoint){
        for (Army a: armyList)
            if (a.getID() == armyID)
                a.setRallyPoint(rallyPoint);
    }

    public String getPosition(String assetID){
        for (Army a: armyList)
            if (a.getID() == assetID)
                return a.getLocation();
        return null;
    }

    //add command into specific structure's queue
    public void addCommand(Command c, String armyID){
        for (Army a: armyList)
            if (a.getID() == armyID)
                a.addCommand(c);
    }

    public void addMoveCommand(Command c, String unitID){
        for (Army a: armyList)
            if (a.getUnit(unitID) != null)
                a.getUnit(unitID).addCommand(c);
    }

    public void executeCommands(){
        for (Army a: armyList) {
        	a.updateArmyTypes();
        	
            if (!a.emptyQueue()) {
                a.executeCommand();
            }
        }
    }

    public void resetCommands(){
        for (Army a: armyList) {
        	if (a.getUnits().size() == 0) {
        		this.decommission(a.assetID);
        	}
            a.resetCommands();
        }
    }

    public Iterator makeIterator(){
        return armyList.iterator();
    }


   
}
