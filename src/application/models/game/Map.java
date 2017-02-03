package application.models.game;

import application.models.tileState.TileState;

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
}
