package application.models.utility;

import application.models.commands.CommandGenerator;
import application.models.playerAsset.Player;
import application.models.tileState.Map;

public class Main {
    public static void main(String args[]) {
    	int length = 4 , width = 4;
        TileGen T = new TileGen(length, width);
        Map m = new Map(T.execute(), length, width);
        m.printOut();
        Player p = new Player();

        CommandGenerator generator = new CommandGenerator();
        generator.generateCommand("CS_U001");

        System.out.println(m.getMap().get("T1").getTileInfo().getTerrainType().getClass().getSimpleName());
    }
}