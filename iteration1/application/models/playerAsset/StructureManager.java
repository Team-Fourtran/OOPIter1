package application.models.playerAsset;

import java.util.ArrayList;

public class StructureManager {
    ArrayList<Structure> structureList;
    final int maxStructures = 10;
    
    public StructureManager(){
        structureList = new ArrayList<>();
    }

    public Structure findStructure(String structureID){
        for (Structure a: structureList){
            if (a.getID() == structureID)
                return a;
        }
        return null;
    }
    
    //intermediate method to find a certain structure's location
    public String getLocation(String structureID){
        for (Structure s : structureList)
            if (s.getID() == structureID)
                return s.getLocation();
        return null;
    }

    //add a new structure to the map on an Army's location
    public Structure createStructure(String location){
        Structure s = new Structure();
        s.setLocation(location);
        structureList.add(s);
        return s;
    }

    //calculate upkeep from all the Player's structures
    public int calculateTotalUpkeep(){
        int totalUpkeep = 0;
        for (Structure s: structureList){
            totalUpkeep += s.getUpkeep();
        }
        return totalUpkeep;
    }

}
