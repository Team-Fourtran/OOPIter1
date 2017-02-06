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
    private ArrayList<Occupance> occupances;
    private HashMap<Directions, TileState> neighbors;

    public HashMap<Directions, TileState> getNeighbors(){
        return neighbors;
    }

    public TileState(String id, TileInfo tile){
    	occupances = new ArrayList<>();
    	neighbors = new HashMap<>();
        this.id = id;
        this.tile = tile;
        neighbors = new HashMap<>(Directions.values().length);
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
    
    public TileInfo getTileInfo() {
    	return tile;
    }

    public String toString() {
        return this.id;
    }
    
    // Returns Map from property type (terrain, item, etc.) to the class names
    public HashMap<String, ArrayList<String>> getProperties() {
    	HashMap<String, ArrayList<String>> properties = new HashMap<String, ArrayList<String>>();
    	
    	ArrayList<String> terrain = new ArrayList<String>();
    	ArrayList<String> aoE = new ArrayList<String>();
    	ArrayList<String> items = new ArrayList<String>();
    	ArrayList<String> units = new ArrayList<String>();
    	ArrayList<String> structures = new ArrayList<String>();
    	ArrayList<String> armies = new ArrayList<String>();
    	
    	// Terrain
    	terrain.add(tile.getTerrainType().getClass().getSimpleName());
    	
    	// AoE
    	for (int i = 0; i < tile.getAoEs().size(); i++) {
    		aoE.add(tile.getAoEs().get(i).getClass().getSimpleName());
    	}
    	
    	// Items (& Decal)
    	for (int i = 0; i < tile.getItems().size(); i++) {
    		if (tile.getItems().get(i) instanceof Decal) {
    			Decal d = (Decal) tile.getItems().get(i);
    			items.add(d.getDecalType());
    		} else {
    			items.add(tile.getItems().get(i).getClass().getSimpleName());
    		}
    	}
    	
    	// PlayerAssets
    	
    	// Units
    	for (int i = 0; i < occupances.size(); i++) {
    		String currentOccupanceID = occupances.get(i).getAssetID();
    		if (currentOccupanceID.charAt(0) == 'u') {
    			units.add(currentOccupanceID);
    		} else if (currentOccupanceID.charAt(0) == 's') {
    			structures.add(currentOccupanceID);
    		} else if (currentOccupanceID.charAt(0) == 'a') {
    			armies.add(currentOccupanceID);
    		}
    	}
    	
    	properties.put("terrain", terrain);
    	properties.put("items", items);
    	properties.put("aoE", aoE);
    	properties.put("units", units);
    	properties.put("structures", structures);
    	properties.put("armies", armies);
    	
    	return properties;
    }
    
    public ArrayList<Occupance> getOccupance() {
    	return occupances;
    }

    public TileState addOccupance(Occupance _o){
        occupances.add(_o);
        _o.updateAssetLocation(this.id);
        return this;
    }

    private void removeOccupance(Occupance _o) {
        occupances.remove(_o);
    }

    public void removeOccupance(String ID){
    	Iterator<Occupance> i = occupances.iterator();
    	Occupance removeMe = null;
        while(i.hasNext()){
        	Occupance _o = i.next();
            if (_o.getAssetID().equals(ID)){
            	removeMe = _o;
            	break;
            }
        }
        removeOccupance(removeMe);
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
        removeOccupance(oldOccupance);
    }
}
