package application.models.utility;

import application.models.commands.Command;
import application.models.commands.CommandGenerator;
import application.models.playerAsset.Player;
import application.models.tileState.Map;

public class Main {
    public static void main(String args[]) {
        TileGen T = new TileGen(4, 4);
        Map m = new Map(T.execute());
        m.printOut();
        Player p = new Player();
        CommandGenerator cGen = new CommandGenerator(p, m);
        for(Command c : cGen.generateCommand("MV_U5_T5_T10")){
            c.execute();
        }
    }
}