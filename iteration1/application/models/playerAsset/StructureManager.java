package application.models.playerAsset;

import application.models.commands.Command;

import java.util.*;

public class StructureManager {
    public ArrayList<Structure> structureList;
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
            if (s.getID().equals(structureID)){
                structureIDs.add(s.getID());
                structureList.remove(s);
            }
        }
    }

    //calculate upkeep from all the Player's structures
    public int calculateTotalUpkeep(){
        int totalUpkeep = 0;
        for (Structure s: structureList){
            totalUpkeep += s.getUpkeep();
        }
        return totalUpkeep;
    }
    
    public void freeID(String assetID) {
    	int escapee = Integer.parseInt(assetID.substring(assetID.lastIndexOf("u") + 1).trim());
    	for (int i = 0; i < structureIDs.size(); i++) {
    		String currentID = structureIDs.get(i);
    		int id = Integer.parseInt(currentID.substring(currentID.lastIndexOf("u") + 1).trim());
    		if (escapee < id) {
    			structureIDs.add(i, assetID);
    			break;
    		}
    	}
    }

    public String getPosition(String assetID){
        for (Structure s: structureList)
            if (s.getID().equals(assetID))
                return s.getLocation();
        return null;
    }

    public boolean structureExists(String structureID){
        for (Structure s: structureList)
            if (s.getID().equals(structureID))
                return true;
        return false;
    }

    //add command into specific structure's queue
    public void addCommand(Command c, String structureID){
        for (Structure s: structureList)
            if (s.getID().equals(structureID))
                s.addCommand(c);
    }

    public void resetCommands(){
        for (Structure s: structureList)
            s.resetCommands();
    }

    //used at beginning of player's turn
    public void executeCommands(){
        for (Structure s: structureList) {
            if (!s.emptyQueue()) {
                s.executeCommand();
            }
        }
    }

    public Iterator makeIterator(){
        return structureList.iterator();
    }

}
