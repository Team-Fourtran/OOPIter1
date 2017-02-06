package application.models.playerAsset;

import java.util.*;

public class ArmyManager {

    ArrayList<Army> armyList;
    final int maxArmies = 10;
    ArrayList<String> armyIDs = new ArrayList<>();
    
    public ArmyManager(){
        armyList = new ArrayList<>();
        for (int i = 1; i <= 10; i++)
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

    public String getPosition(String assetID){
        for (Army a: armyList)
            if (a.getID() == assetID)
                return a.getLocation();
        return null;
    }

    //public void executeCommands(){}

    public Iterator makeIterator(){
        return armyList.iterator();
    }


   
}
