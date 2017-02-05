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

    public String getPosition(String assetID) {
        return "T6";
    }

    //method to do maintenence tasks on player's assets
    public void beginTurn(){
        int totalFoodCost = units.calculateTotalUpkeep() + armies.calculateTotalUpkeep();
        int totalWoodCost = structures.calculateTotalUpkeep();
        food -= totalFoodCost;
        wood -= totalWoodCost;
        //TO-DO: enforce some punishment for not having enough
    }

    //method to pass list of units to army manager to form army
    public void formArmy(ArrayList<Unit> units, String rallyPoint){
        armies.formArmy(units, rallyPoint);
    }

    //method to decommission army, recieve released units, and pass them to unit manager
    public void decommissionArmy(String armyID){
        ArrayList<Unit> releasedUnits = armies.decommission(armyID);
        units.addUnits(releasedUnits);
    }
    //method to check a specific army for a colonist, create a structure on that tile,
    //and consume the colonist
    public boolean canCreateStructure(String armyID){
        return armies.findArmy(armyID).hasColonist();
    }

    public Structure createStructure(String armyID){
        String location = armies.findArmy(armyID).getLocation();
        armies.findArmy(armyID).removeColonist();
        return structures.createStructure(location);
    }

    public boolean canCreateUnit(String armyID){
        return (units.unitCount < units.maxUnits);
    }

    //method to place a new unit on the map through an existing structure
    public Unit createUnit(String structureID, String type){
        String unitLoc = structures.getLocation(structureID);
        return units.addNewUnit(type, unitLoc);
        //TO-DO: check to see if creation is valid

    }
}
