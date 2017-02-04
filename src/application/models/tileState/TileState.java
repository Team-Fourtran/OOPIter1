package application.models.tileState;

import java.util.HashMap;

public class TileState {
    private String id;
    //private TileInfo tile
    private ArmyOccupance armyOccupance;
    private StructureOccupance structureOccupance;
    private UnitOccupance unitOccupance;
    private HashMap<Directions, TileState> neighbors;

    public TileState(String id){
        this.id = id;
        neighbors = new HashMap<Directions, TileState>(Directions.values().length);
        for (Directions d : Directions.values()){
            neighbors.put(d, null);
        }
    }

    public void printState(){
        System.out.println(this.id + " -- " + neighbors.keySet().toString() + neighbors.values().toString());
    }

    public void setNeighbor(TileState originator, Directions direction){
        neighbors.put(direction, originator);
    }

    public String getId(){
        return this.id;
    }

    public String toString(){
        return this.id;
    }
}
