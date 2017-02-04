package application.models.tileState;


import application.models.playerAsset.PlayerAsset;

import java.util.HashMap;

public class Map {
    private java.util.Map<String, TileState> tiles;
    private int length;
    private int width;

    public Map(TileState[] states, int length, int width){
    	this.length = length;
    	this.width = width;
        tiles = new HashMap<String, TileState>(states.length);
        for(TileState _ts : states) {
            tiles.put(_ts.getId(), _ts);
        }
    }

    public void printOut(){
        tiles.values().forEach(TileState::printState);
    }

    public void addAsset(String tileID, PlayerAsset asset){

    }

    //View Interface:
    
    public int getLength() {
    	return length;
    }
    
    public int getWidth() {
    	return width;
    }
    
    // Get the Map from tile id to TileState
    public java.util.Map<String, TileState> getTiles() {
    	return tiles;
    }

}