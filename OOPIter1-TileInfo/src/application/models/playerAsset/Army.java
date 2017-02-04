package application.models.playerAsset;
import java.util.ArrayList;

//Responsibilities: Group units, hold rally point,
public class Army implements PlayerAsset{
    
    ArrayList<Unit> battleGroup;
    ArrayList<Unit> reinforcements;
    String RallyPoint;
    String ID;
    
    //placeholder constructor
    public Army(ArrayList<Unit> units){
        battleGroup = units;
        
    }
    
    public void setID(String id){
        ID = id;
    }
    
    public String getID(){
        return ID;
    }
    
    //return all units in the army
    public ArrayList<Unit> getUnits(){
        ArrayList<Unit> newList = battleGroup;
        newList.addAll(reinforcements);
        return newList;
    }
    
    public void removeUnit(Unit u){
        if (battleGroup.contains(u)){
            battleGroup.remove(u);
            //decrement unit count
        }
        if (reinforcements.contains(u)){
            reinforcements.remove(u);
            //decrement unit count
        }
    }
    
    public void removeColonist(){
        for (Unit i: battleGroup)
            if (i instanceof Colonist)
                battleGroup.remove(i);
    }
    
    public boolean hasColonist(){
        for (Unit i: battleGroup)
            if (i instanceof Colonist)
                return true;
        return false;
    }
}
