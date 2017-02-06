package application.models.tileState;

import application.models.utility.AStarPathfinder;

import java.util.HashMap;
/*
 * Class for containing TileStates and optimal path between tiles
 */
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

    public TileState getTileState(String tileID){
        return tiles.get(tileID);
    }

    // Gerneate degrees representing the optimal path to get from start to end
    public String generatePath(String startTileID, String endTileID) {
        AStarPathfinder path = new AStarPathfinder(
                tiles.get(startTileID),
                tiles.get(endTileID)
        );
        return path.execute();
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