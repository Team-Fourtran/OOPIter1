package application.models.tileState;

import java.util.HashMap;
import java.util.List;

public class TileState {
    private String id;
    //private TileInfo tile
    private List<Occupance> occupances;
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

    public TileState addOccupance(Occupance _o){
        occupances.add(_o);
        return this;
    }

    private void removeOccupance(Occupance _o){
        occupances.remove(_o);
    }

    public void removeOccupance(String ID){
        for(Occupance _o : occupances){
            if (_o.getAssetID().equals(ID)){
                removeOccupance(_o);
            }
        }
    }

    public Occupance getOccupance(String assetID){
        for(Occupance _o : occupances){
            if (_o.getAssetID().equals(assetID)){
                return _o;
            }
        }
        return null;
    }

    public void moveOccupance(String id, Directions direction){
        for (Occupance _o : occupances){
            if(id == _o.getAssetID()){
                neighbors.get(direction).addOccupance(_o);
                removeOccupance(_o);
            }
        }
    }
}
