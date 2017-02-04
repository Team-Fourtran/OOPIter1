import java.util.ArrayList;

public class UnitManager {
    
    ArrayList<Unit> unitList;
    final int maxUnits = 25;
    
    public UnitManager(){
        unitList = new ArrayList<>();
    }
    
    public void addUnits(ArrayList<Unit> units){
        unitList.addAll(units);
    }
}
