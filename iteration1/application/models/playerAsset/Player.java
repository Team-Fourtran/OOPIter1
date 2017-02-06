package application.models.playerAsset;

import application.models.commands.Command;

import java.util.*;
import application.*;
public class Player {
    
    private final ArmyManager armies;
    private final UnitManager units;
    private final StructureManager structures;
    private int food;
    private int wood;
    private Game game;
    
    public Player(){
        armies = new ArmyManager();
        units = new UnitManager();
        structures = new StructureManager();
        food = 0;
        wood = 0;

    }

    public void notify(Command command){
        System.out.println("Received command");
        command.execute();
    }
    public void notify(String assetID, Command command){
    	command.execute();
    }

    public void setGame(Game game){
    	this.game = game;
    }

    //method to do maintenence tasks on player's assets
    public void beginTurn(){
        int totalFoodCost = units.calculateTotalUpkeep() + armies.calculateTotalUpkeep();
        int totalWoodCost = structures.calculateTotalUpkeep();
        food -= totalFoodCost;
        wood -= totalWoodCost;
        armies.executeCommands();
        structures.executeCommands();
    }
    public void endTurn(){
        armies.resetCommands();
        structures.resetCommands();
        game.switchPlayers();
    }
    //pass list of units to army manager to form army
    public Army formArmy(ArrayList<String> unitIDs, String rallyPoint){
    	ArrayList<Unit> u = new ArrayList<Unit>();
    	for (int i = 0; i < unitIDs.size(); i++) {
    		// If the Player has this unitID, add it to the army
    		if (this.getUnit(unitIDs.get(i)) != null) {
    			u.add(this.getUnit(unitIDs.get(i)));
    		}
    	}
        return armies.formArmy(u, rallyPoint);
    }

    //decommission army, recieve released units, and pass them to unit manager
    public void decommissionArmy(String armyID){
        ArrayList<Unit> releasedUnits = armies.decommission(armyID);
        units.addUnits(releasedUnits);
    }

    public void setRallyPoint(String armyID, String rallyPoint){
        armies.setRallyPoint(armyID, rallyPoint);
    }

    //check to see if the structure creation is valid
    public String canCreateStructure(String armyID){
    	if (armies.findArmy(armyID).hasColonist() != null) {
    		return armies.findArmy(armyID).hasColonist();
    	} else {
    		return null;
    	}
    }

    public ArrayList<String> getUnitIDs(String armyID){
        ArrayList<Unit> units = armies.findArmy(armyID).getUnits();
        ArrayList<String> unitIDs = new ArrayList<String>();
        for (Unit u: units)
            unitIDs.add(u.getID());
        return unitIDs;
    }

    //check a specific army for a colonist, create a structure on that tile,
    //and consume the colonist
    public Structure createStructure(String armyID){
        if (canCreateStructure(armyID) != null) {
            String location = armies.findArmy(armyID).getLocation(); //Can be removed, added to params if controller can send it!
            armies.findArmy(armyID).removeColonist();
            Structure s = structures.createStructure(location);
            return s;
        }
        return null;
    }

    public void decommissionStructure(String structureID){
        structures.decommission(structureID);
    }

    public void healUnits(String structureID){
        structures.healUnits(structureID);
    }

    public boolean canCreateUnit(String structureID, String type){
        return (structures.structureExists(structureID) && units.checkIfValid(type));
    }
    
    //method to place a new unit on the map through an existing structure
    public Unit createUnit(String type, String structureID){
        if (units.checkIfValid(type)) {
            String unitLoc = structures.getPosition(structureID);
            return units.addNewUnit(unitLoc, type);
            //TO-DO: check to see if creation is valid
        }
        return null;
    }

    //create initial unit(s) at beginning of the game
    public Unit createInitialUnit(String tileID, String type){
        return units.addNewUnit(tileID, type);
    }

    public void decommissionUnit(String unitID){
        units.decommissionUnit(unitID);
    }

    //get current position of a specific PlayerAsset
    public String getPosition(String assetID){
        if (assetID.charAt(0) == 'u')
            return units.getPosition(assetID);
        else if (assetID.charAt(0) == 'a')
             return armies.getPosition(assetID);
        else if (assetID.charAt(0) == 's')
            return structures.getPosition(assetID);
        else
            return ("No asset with that ID found");
    }
    
    /*
     * Recycle asset id
     */
    public void freeFromSuffering(String assetID) {
        if (assetID.charAt(0) == 'u')
            units.freeID(assetID);
        else if (assetID.charAt(0) == 'a')
             armies.freeID(assetID);
        else if (assetID.charAt(0) == 's')
            structures.freeID(assetID);
    }

    public Iterator getUnitIterator(){
        return units.makeIterator();
    }

    public Iterator getArmyIterator(){
        return armies.makeIterator();
    }

    public Iterator getStructureIterator(){
        return structures.makeIterator();
    }
    
    // Used primarily in army formation currently, so that Units can be added to an army
    private Unit getUnit(String unitID) {
		Iterator<?> it = getUnitIterator();
		while(it.hasNext()) {
			Unit unit = (Unit) it.next();
			if (unit.getID().equals(unitID)) {
				return unit;
			}
		}
		return null;
    }
}
