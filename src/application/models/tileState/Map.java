package application.models.tileState;


import application.models.playerAsset.PlayerAsset;

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

    public void addAsset(String tileID, PlayerAsset asset){

    }

    //View Interface:
    //...

}