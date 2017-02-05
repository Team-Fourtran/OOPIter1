package tests;

import application.models.commands.Command;
import application.models.commands.CommandGenerator;
import application.models.playerAsset.Player;
import application.models.playerAsset.Unit;
import application.models.playerAsset.UnitManager;
import application.models.tileState.Map;
import application.models.tileState.TileState;
import application.models.utility.TileGen;

public class UnitMovement {

	public static void main(String[] args) {
    	int length = 4 , width = 4;
        TileGen T = new TileGen(length, width);
        Map m = new Map(T.execute(), length, width);
        m.printOut();
        Player p = new Player();
        
        CommandGenerator cGen = new CommandGenerator(p, m);   
        
        // Adding unit to tile T0
        Command c = cGen.generateCommand("IU_0_T0");
        c.execute();
        
        // Move unit 0 from T0 to T1
        c = cGen.generateCommand("MV_0_T1");
        c.execute();
        
	}

}
