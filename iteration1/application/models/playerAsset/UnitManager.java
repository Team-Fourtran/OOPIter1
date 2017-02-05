package application.models.playerAsset;

import java.util.ArrayList;
import java.util.*;

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
    public Unit addNewUnit(String unitLocation, String type){
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

    public String getPosition(String assetID){
        for (Unit u: unitList) {
            if (u.getID().equals(assetID)) {
                return u.getLocation();
            }
        }
        return null;
    }

    public Iterator makeIterator(){
        return unitList.iterator();
    }


}
