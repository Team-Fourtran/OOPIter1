package application.models.playerAsset;

import application.models.commands.Command;

import java.util.*;

public class StructureManager {
    ArrayList<Structure> structureList;
    final int maxStructures = 10;
    ArrayList<String> structureIDs = new ArrayList<>();

    
    public StructureManager(){
        structureList = new ArrayList<>();
        for (int i = 1; i <= 10; i++)
            structureIDs.add("s" + i);
    }

    public int getStructureCount(){
        return structureList.size();
    }

    //add a new structure to the map on an Army's location
    public Structure createStructure(String location){
        Structure s = new Structure();
        s.setLocation(location);
        s.setID(structureIDs.get(0));
        structureList.add(s);
        structureIDs.remove(0);
        return s;
    }

    public void decommission(String structureID){
        for (Structure s: structureList){
            if (s.getID() == structureID){
                structureIDs.add(s.getID());
                structureList.remove(s);
            }
        }
    }

    public void heal(String structureID, Unit u){
        for (Structure s: structureList)
            if (s.getID() == structureID)
                s.healUnit(u);
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

    public boolean structureExists(String structureID){
        for (Structure s: structureList)
            if (s.getID() == structureID)
                return true;
        return false;
    }

    //add command into specific structure's queue
    public void addCommand(Command c, String structureID){
        for (Structure s: structureList)
            if (s.getID() == structureID)
                s.addCommand(c);
    }

    //used at beginning of player's turn
    public void executeCommands(){
        for (Structure s: structureList)
            s.executeCommand();
    }

    public Iterator makeIterator(){
        return structureList.iterator();
    }

}
