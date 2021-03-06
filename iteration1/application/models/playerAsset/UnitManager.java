package application.models.playerAsset;

import java.util.*;

/* Manager for a Player's Units
   Helps create units and pass commands to them
 */
public class UnitManager {
    
    ArrayList<Unit> unitList;
    UnitFactory factory;
    int unitCount;
    int meleeCount;
    int rangedCount;
    int explorerCount;
    int colonistCount;
    final int maxUnits = 25;
    final int maxUnitType = 10;
    static ArrayList<String> unitIDs = new ArrayList<String>();

    public UnitManager(){
        unitList = new ArrayList<>();
        unitCount = 0;
        factory = new UnitFactory();
        for (int i = 1; i <= 50; i++)
            unitIDs.add("u" + i);
    }

    //add a new unit to the map on the structure's location that created it
    public Unit addNewUnit(String unitLocation, String type){
        Unit newUnit = factory.makeUnit(type);
        newUnit.setLocation(unitLocation);
        newUnit.setID(unitIDs.get(0));
        unitIDs.remove(0);
        unitList.add(newUnit);
        unitCount++;
        incrementUnit(type);
        return newUnit;
    }

    //method to add units from disbanded army into the unit list
    public void addUnits(ArrayList<Unit> units){
        unitList.addAll(units);
    }

    public void decommissionUnit(String unitID){
        for (Unit u: unitList)
            if (u.getID().equals(unitID)){
                unitIDs.add(u.getID());
                if (u instanceof MeleeUnit)
                    decrementUnit("melee");
                else if (u instanceof RangedUnit)
                    decrementUnit("ranged");
                else if (u instanceof Explorer)
                    decrementUnit("explorer");
                else
                    decrementUnit("colonist");
                unitList.remove(u);
            }
    }

    //Calculate upkeep from all of the Player's free units
    public int calculateTotalUpkeep(){
        int totalUpkeep = 0;
        for (Unit u: unitList){
            totalUpkeep += u.getUpkeep();
        }
        return totalUpkeep;
    }

    //recycle an ID of a unit who doesn't need one anymore
    public void freeID(String assetID) {
    	int escapee = Integer.parseInt(assetID.substring(assetID.lastIndexOf("u") + 1).trim());
    	for (int i = 0; i < unitIDs.size(); i++) {
    		String currentID = unitIDs.get(i);
    		int id = Integer.parseInt(currentID.substring(currentID.lastIndexOf("u") + 1).trim());
    		if (escapee < id) {
    			unitIDs.add(i, assetID);
    			break;
    		}
    	}
    }
    
    //find position of unit on the map
    public String getPosition(String assetID){
        for (Unit u: unitList) {
            if (u.getID().equals(assetID)) {
            	
                return u.getLocation();
            }
        }
        return null;
    }

    //depending on the type of unit made, increment that count
    public void incrementUnit(String type){
        switch(type){
            case "melee": meleeCount++;
            case "ranged": rangedCount++;
            case "explorer": explorerCount++;
            case "colonist": colonistCount++;
        }
    }

    //decrement count of a decommissioned unit based on its type
    public void decrementUnit(String type){
        switch(type){
            case "melee": meleeCount--;
            case "ranged": rangedCount--;
            case "explorer": explorerCount--;
            case "colonist": colonistCount--;
        }
    }

    //check if unit creation is valid
    public boolean checkIfValid(String type){
        if (unitCount < 25){
            switch(type){
                case "melee": if (meleeCount < 10) return true;
                case "ranged": if (rangedCount < 10) return true;
                case "explorer": if (explorerCount < 10) return true;
                case "colonist": if (colonistCount < 10) return true;
            }
        }
        return true;
    }

    //get a specific unit based on ID
    public Unit getUnit(String unitID){
        for (Unit u: unitList)
            if (u.getID().equals(unitID))
                return u;
        return null;
    }

    //if possible, execute a (movement) command in units
    public void executeCommands(){
        for (Unit u: unitList)
            if (!u.emptyQueue())
                u.executeCommand();
    }

    //reset the ability for a unit to move
    public void resetCommands(){
        for (Unit u: unitList)
            u.resetCommands();
    }

    public ListIterator makeListIterator(){
        return unitList.listIterator();
    }


}
