package application.models.utility;

import application.models.commands.CommandGenerator;
import application.models.playerAssets.Player;
import application.models.tileState.Map;

public class Main {
    public static void main(String args[]) {
        TileGen T = new TileGen(4, 4);
        Map m = new Map(T.execute());
        m.printOut();
        Player p = new Player();

        CommandGenerator generator = new CommandGenerator();
        generator.generateCommand("CS_U001");

    }
}