package tests;

import application.models.tileState.Map;
import application.models.utility.TileGen;

public class TestPathGen {
    public static void main(String[] args){
        int ROW = 15;
        int COL = 15;

        TileGen T = new TileGen(ROW, COL);
        Map testMap = new Map(T.execute(), ROW, COL);


    }
}
