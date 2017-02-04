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

    //add a new unit to the map on the structure's location that created it
    public void addNewUnit(String type, String unitLocation){
        Unit newUnit = factory.makeUnit(type);
        newUnit.setLocation(unitLocation);
        unitList.add(newUnit);
        unitCount++;
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
