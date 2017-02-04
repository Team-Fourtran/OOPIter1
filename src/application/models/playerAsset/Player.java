package application.models.playerAsset;

import java.util.ArrayList;

public class Player {
    
    private final ArmyManager armies;
    private final UnitManager units;
    private final StructureManager structures;
    private int food;
    private int wood;
    
    
    public Player(){
        armies = new ArmyManager();
        units = new UnitManager();
        structures = new StructureManager();
        food = 0;
        wood = 0;
        
        System.out.println("Player created");
    }
    
    public void decommisionArmy(int armyID){
        ArrayList<Unit> releasedUnits = armies.decommision(armyID);
        units.addUnits(releasedUnits);
    }

    public boolean canCreateStructure(String armyID){
        return armies.findArmy(armyID).hasColonist();
    }
    
    public Structure createStructure(String armyID){
        armies.findArmy(armyID).removeColonist();
        return structures.createStructure();
    }
    
    public void formArmy(ArrayList<Unit> u){
        armies.formArmy(u);
    }
        
        
}
