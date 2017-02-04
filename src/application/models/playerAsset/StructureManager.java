package application.models.playerAsset;

import java.util.ArrayList;

public class StructureManager {
    ArrayList<Structure> structureList;
    final int maxStructures = 10;
    
    public StructureManager(){
        structureList = new ArrayList<>();
    }
    
    public Structure createStructure(){
        Structure s = new Structure();
        structureList.add(s);
        return s;
    }
}
