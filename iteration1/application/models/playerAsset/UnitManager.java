package application.models.playerAsset;

import java.util.ArrayList;

public class UnitManager {
    
    ArrayList<Unit> unitList;
    int unitCount;
    UnitFactory factory;
    final int maxUnits = 25;

    public UnitManager(){
        unitList = new ArrayList<>();
        unitCount = 0;
        factory = new UnitFactory();
    }
    
    public ArrayList<Unit> getUnitList() {
    	return unitList;
    }
    
    public Unit findUnit(String unitID){
        for (Unit a: unitList){
            if (a.getID().equals(unitID))
                return a;
        }
        return null;
    }

    //add a new unit to the map on the structure's location that created it
    public Unit addNewUnit(String type, String unitLocation){
        Unit newUnit = factory.makeUnit(type);
        newUnit.setLocation(unitLocation);
        unitList.add(newUnit);
        unitCount++;
        return newUnit;
    }

    //method to add units from disbanded army into the unit list
    public void addUnits(ArrayList<Unit> units){
        unitList.addAll(units);
    }

    //Calculate upkeep from all of the Player's free units
    public int calculateTotalUpkeep(){
        int totalUpkeep = 0;
        for (Unit u: unitList){
            totalUpkeep += u.getUpkeep();
        }
        return totalUpkeep;
    }
}
