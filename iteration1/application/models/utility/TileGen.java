package application.models.utility;

import application.models.tileState.Directions;
import application.models.tileState.TileState;

public class TileGen {
    private int length;
    private int width;
    private int total;

    TileGen(int l, int w){
        this.length = l;
        this.width = w;
        this.total = l*w;
    }

    TileState[] execute(){
        TileState[] states = new TileState[total];
        for (int i = 0; i < total; i++){
            states[i] = new TileState("T" + i);
        }
        for (int i = 0; i < total; i++) {

            if(i-length >= 0)
                states[i].setNeighbor(states[i-length], Directions.N);
            if((i+1)%length != 0 && i-length >= 0)
                states[i].setNeighbor(states[i-length+1], Directions.NE);
            if((i+1)%length != 0)
                states[i].setNeighbor(states[i+1], Directions.E);
            if((i+1)%length != 0 && i+length < total)
                states[i].setNeighbor(states[i+length+1], Directions.SE);
            if(i+length < total)
                states[i].setNeighbor(states[i+length], Directions.S);
            if(i%length != 0 && i+length < total)
                states[i].setNeighbor(states[i+length-1], Directions.SW);
            if(i%length != 0)
                states[i].setNeighbor(states[i-1], Directions.W);
            if(i%length != 0 && i-length >= 0)
                states[i].setNeighbor(states[i-length-1], Directions.NW);
        }
        return states;
    }


    private void setNeighbor(int x){
        if (x >= 0 && x < total){

        }
    }
}
