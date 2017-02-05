package application.models.tileState;

import java.util.HashMap;

public class Map {
    private java.util.Map<String, TileState> tiles;

    public Map(TileState[] states){
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

    public String generatePath(String startTileID, String endTileID) {
        return "N_N_N_N_W_W_S_W";
    }

    //View Interface:
    //...

}