package application.models.playerAsset;

import application.models.commands.Command;

import java.util.*;

public class Army extends PlayerAsset {
    
    ArrayList<Unit> battleGroup = new ArrayList<Unit>();
    ArrayList<Unit> reinforcements = new ArrayList<Unit>();
    String rallyPoint;

    public Army(ArrayList<Unit> units, String rallyPoint) {
    	System.out.println("us: " + units);
        this.rallyPoint = rallyPoint;
        for (Unit u: units){
            if (u.getLocation().equals(rallyPoint)) {
                battleGroup.add(u);
                System.out.println("bg: " + battleGroup);
            }
            else {
                reinforcements.add(u);
            }
            System.out.println("BG: " + battleGroup);
            System.out.println("RI: " + reinforcements);
        }
    }

    //method to set new rally point. If any reinforcements are on that tile
    //put them in the battle group
    public void setRallyPoint(String location){
        rallyPoint = location;
        for (Unit u: reinforcements)
            if (u.getLocation().equals(rallyPoint)){
                battleGroup.add(u);
                reinforcements.remove(u);
            }
    }

    //return all units in the army
    public ArrayList<Unit> getUnits(){
        ArrayList<Unit> newList = new ArrayList<>();
        newList.addAll(battleGroup);
        newList.addAll(reinforcements);
        return newList;
    }

    //method to check if an army has a colonist to make a structure
    public String hasColonist() {
        for (Unit i: battleGroup)
            if (i instanceof Colonist)
                return i.getID();
        return "";
    }

    //after a structure is made, remove the colonist from the army
    public void removeColonist() {
    	Unit removed = null;
        for (Unit i: battleGroup) {
            if (i instanceof Colonist)
            	removed = i;
        }
        battleGroup.remove(removed);
    }

    //see if any reinforcements arrived at the rally point
    //to be called each turn
    public void updateArmyTypes(){
    	Unit removeMe = null;
        for (Unit u: reinforcements) {
            if (u.getLocation().equals(rallyPoint)){
                battleGroup.add(u);
                removeMe = u;
            }
        }
        
        reinforcements.remove(removeMe);
        System.out.println("BG2: " + battleGroup);
        System.out.println("RI2: " + reinforcements);
    }

    public Unit getUnit(String unitID){
        ArrayList<Unit> units = getUnits();
        for (Unit u: units)
            if (u.getID() == unitID)
                return u;
        return null;
    }
}
