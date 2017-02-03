package application.models.tileState;

import application.models.playerAssets.*;

public class Map {
    private java.util.Map<String, TileState> tiles;

    public Map(TileState[] states){
        tiles = new java.util.HashMap<String, TileState>(states.length);
        for(TileState _ts : states) {
            tiles.put(_ts.getId(), _ts);
        }
    }

    public void printOut(){
        tiles.values().forEach(TileState::printState);
    }

    public void add(String tileID, Unit _unit){

    }
    public void add(String tileID, Structure _struct){

    }
    public void add(String tileID, Army _army){

    }
}