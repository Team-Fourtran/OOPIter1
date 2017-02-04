package application.models.tileState;

import java.util.HashMap;

public class TileState {
    private String id;
    //private TileInfo tile
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

    public void setNeighbor(TileState neighbor, Directions direction){
        neighbors.put(direction, neighbor);
    }

    public String getId(){
        return this.id;
    }

    public String toString(){
        return this.id;
    }
}
