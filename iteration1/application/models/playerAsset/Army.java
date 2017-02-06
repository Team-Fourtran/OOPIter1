package application.models.playerAsset;

import application.models.commands.Command;

import java.util.*;

/*Defines an Army, consisting of two groups of Units and a Rally Point
  Battle group units have met at the Rally Point and are able to move together and fight
  Reinforcements are units on the way to a Rally Point
 */
public class Army extends PlayerAsset {
    
    ArrayList<Unit> battleGroup = new ArrayList<Unit>();
    ArrayList<Unit> reinforcements = new ArrayList<Unit>();
    String rallyPoint;

    public Army(ArrayList<Unit> units, String rallyPoint) {
        this.rallyPoint = rallyPoint;
        for (Unit u: units){
            if (u.getLocation().equals(rallyPoint)) {
                battleGroup.add(u);
            }
            else {
                reinforcements.add(u);
            }
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

    //return all units in the army from both lists
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
    }

    //given a UnitID, return the unit if exists in this army
    public Unit getUnit(String unitID){
        ArrayList<Unit> units = getUnits();
        for (Unit u: units)
            if (u.getID().equals(unitID))
                return u;
        return null;
    }
}
