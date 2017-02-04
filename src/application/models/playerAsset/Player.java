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
    
    public void createStructure(int armyID){
        if(armies.findArmy(armyID).hasColonist()){
            structures.createStructure();
            armies.findArmy(armyID).removeColonist();
        }
    }
    
    public void formArmy(ArrayList<Unit> u){
        armies.formArmy(u);
    }
        
        
}
