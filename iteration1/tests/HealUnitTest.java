package tests;

import application.models.commands.CommandGenerator;
import application.models.playerAsset.Player;
import application.models.tileState.Map;
import application.models.utility.TileGen;

public class HealUnitTest {

	public static void main(String[] args) {
		// Create a colonist unit
    	int length = 4 , width = 4;
        TileGen T = new TileGen(length, width);
        Map m = new Map(T.execute(), length, width);
        m.printOut();
        Player p = new Player();
        
        CommandGenerator cGen = new CommandGenerator(p, m);
        cGen.generateCommand("IU_T0_colonist");
        
        System.out.println(m.getTileState("T0").getProperties());
        
        cGen.generateCommand("NA_T1_u1");
        System.out.println(m.getTileState("T1").getProperties());

        // Get the army, once updated
        // With the armyID, create a structure from that
        // Then create another unit on the same tile T1
        // Have structure heal that entire tile
        // Player.healUnits(structureID, ArrayList<String> siblingAssetIDs)
	}

}
