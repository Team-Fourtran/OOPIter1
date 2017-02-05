package tests;

import java.util.ArrayList;

import application.models.commands.Command;
import application.models.commands.CommandGenerator;
import application.models.playerAsset.Player;
import application.models.playerAsset.Unit;
import application.models.tileState.Map;
import application.models.utility.TileGen;

public class NewStructureTest {

	public static void main(String[] args) {

		// Create a colonist unit
    	int length = 4 , width = 4;
        TileGen T = new TileGen(length, width);
        Map m = new Map(T.execute(), length, width);
        m.printOut();
        Player p = new Player();
        
        CommandGenerator cGen = new CommandGenerator(p, m);   
        
        // Adding unit to tile T0
        Command c = cGen.generateCommand("IU_0_T0_colonist").get(0);
        c.execute();
        
        // Retrieve that unit reference.
        
        // Create an army containing that colonist unit
        ArrayList<String> units = new ArrayList<String>();
        units.add("u1");
        p.formArmy(units, "T0");
	}

}
