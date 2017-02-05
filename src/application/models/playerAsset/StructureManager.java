package application.models.playerAsset;

import java.util.*;

public class StructureManager {
    ArrayList<Structure> structureList;
    final int maxStructures = 10;
    
    public StructureManager(){
        structureList = new ArrayList<>();
    }

    //intermediate method to find a certain structure's location
    public String getLocation(String structureID){
        for (Structure s : structureList)
            if (s.getID() == structureID)
                return s.getLocation();
        return null;
    }

    //add a new structure to the map on an Army's location
    public void createStructure(String location){
        Structure s = new Structure();
        s.setLocation(location);
        structureList.add(s);
    }

    //calculate upkeep from all the Player's structures
    public int calculateTotalUpkeep(){
        int totalUpkeep = 0;
        for (Structure s: structureList){
            totalUpkeep += s.getUpkeep();
        }
        return totalUpkeep;
    }

    public String getPosition(String assetID){
        for (Structure s: structureList)
            if (s.getID() == assetID)
                return s.getLocation();
        return null;
    }

    public Iterator makeIterator(){
        return structureList.iterator();
    }

}
