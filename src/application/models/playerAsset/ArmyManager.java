package application.models.playerAsset;

import java.util.ArrayList;

public class ArmyManager {

    ArrayList<Army> armyList;
    final int maxArmies = 10;
    ArrayList<String> armyIDs = new ArrayList<>();
    
    public ArmyManager(){
        armyList = new ArrayList<>();

        for (int i = 0; i < 10; i++)
            armyIDs.add("a" + i);
        }

    //create a new army with given units and add it to the armyList
    public void formArmy(ArrayList<Unit> units, String rallyPoint){
        Army newArmy = new Army(units, rallyPoint);
        newArmy.setID(armyIDs.get(0));
        //TO-DO: set location of army (battle group?)
        armyList.add(newArmy);
        armyIDs.remove(0);

    }
    public Army findArmy(String armyID){
        for (Army a: armyList){
            if (a.getID() == armyID)
                return a;
        }
        return null;
    }
    
    //delete army from armyList and return the units
    public ArrayList<Unit> decommission(String ArmyID){
        ArrayList<Unit> units = new ArrayList<>();
        for (Army army: armyList){
            if (Integer.parseInt(army.getID()) == ArmyID){
                units = army.getUnits();
                armyIDs.add(army.getID());
                armyList.remove(army);
            }    
        }
        return units;
    }

    //calculate total upkeep from each army, unit by unit
    public int calculateTotalUpkeep(){
        int totalUpkeep = 0;
        for (Army a: armyList){
            ArrayList<Unit> units = a.getUnits();
            for (Unit u: units)
                totalUpkeep += u.getUpkeep();
        }
        return totalUpkeep;
    }

    //debugging
    public void displayArmy(){
        System.out.println("There are " + armyList.size() + "armies for this player");
        for (int i = 0; i < armyList.size(); i++){
            System.out.println("Army " + i);
        }
    }

   
}
