package application.models.tileState;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import application.models.tileInfo.*;
import java.util.List;

public class TileState {
    private String id;
    private TileInfo tile;
    //private TileInfo tile
    private List<Occupance> occupances;
    private HashMap<Directions, TileState> neighbors;

    public TileState(String id, TileInfo tile){
    	occupances = new ArrayList<Occupance>();
    	neighbors = new HashMap<Directions, TileState>();
        this.id = id;
        this.tile = tile;
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
    
    public Directions getNeighborDirection(String neighbor) {
    	for (Directions d : Directions.values()) {
    		if ((neighbors.get(d) != null) && neighbors.get(d).getId().equals(neighbor)) {
    			return d;
    		}
    	}
    	return null;
    }

    public String getId(){
        return this.id;
    }
    
    public TileInfo getTileInfo() {
    	return tile;
    }

    public String toString(){
        return this.id;
    }
    
    // Returns Map from property type (terrain, item, etc.) to the class names
    public HashMap<String, ArrayList<String>> getProperties() {
    	HashMap<String, ArrayList<String>> properties = new HashMap<String, ArrayList<String>>();
    	
    	ArrayList<String> terrain = new ArrayList<String>();
    	ArrayList<String> aoE = new ArrayList<String>();
    	ArrayList<String> items = new ArrayList<String>();
    	
    	terrain.add(tile.getTerrainType().getClass().getSimpleName());
    	
    	for (int i = 0; i < tile.getAoEs().size(); i++) {
    		aoE.add(tile.getAoEs().get(i).getClass().getSimpleName());
    	}
    	
    	for (int i = 0; i < tile.getItems().size(); i++) {
    		if (tile.getItems().get(i) instanceof Decal) {
    			Decal d = (Decal) tile.getItems().get(i);
    			items.add(d.getDecalType());
    		} else {
    			items.add(tile.getItems().get(i).getClass().getSimpleName());
    		}
    	}
    	
    	properties.put("terrain", terrain);
    	properties.put("items", items);
    	properties.put("aoE", aoE);
    	
    	return properties;
    }
    
    public List<Occupance> getOccupance() {
    	return occupances;
    }

    public TileState addOccupance(Occupance _o){
        occupances.add(_o);
        return this;
    }

    private void removeOccupance(Occupance _o){
        occupances.remove(_o);
    }

    public void removeOccupance(String ID){
    	Iterator<Occupance> i = occupances.iterator();
        while(i.hasNext()){
        	Occupance _o = i.next();
            if (_o.getAssetID().equals(ID)){
                removeOccupance(_o);
            }
        }
    }

    public Occupance getOccupance(String assetID){
    	Iterator<Occupance> i = occupances.iterator();
        while(i.hasNext()){
        	Occupance _o = i.next();
            if (_o.getAssetID().equals(assetID)){
                return _o;
            }
        }
        return null;
    }

    public void moveOccupance(String id, Directions direction){
    	Occupance oldOccupance = null;
    	Iterator<Occupance> i = occupances.iterator();
        while(i.hasNext()){
        	Occupance _o = i.next();
            if(id.equals(_o.getAssetID())){
                neighbors.get(direction).addOccupance(_o);
                oldOccupance = _o;
            }
        }
        // Out here to avoid concurrent modification of ArrayLists
        if (oldOccupance != null) {
        	removeOccupance(oldOccupance);
        }
    }
}
