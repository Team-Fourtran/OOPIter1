package tests;

import application.models.commands.Command;
import application.models.commands.CommandGenerator;
import application.models.playerAsset.Player;
import application.models.tileState.Map;
import application.models.utility.TileGen;

/*
 * This is a test class for initializing a colonist unit and having it move E(0)
 */
public class UnitMovement {

	public static void main(String[] args) {
    	int length = 4 , width = 4;
        TileGen T = new TileGen(length, width);
        Map m = new Map(T.execute(), length, width);
        m.printOut();
        Player p = new Player();
        
        CommandGenerator cGen = new CommandGenerator(p, m);   
        
        // Adding unit to tile T0
        Command c = cGen.generateCommand("IU_0_T0_colonist").get(0);
        c.execute();
        
        // Move unit 0 from T0 to T1
        c = cGen.generateCommand("MD_u0_0").get(0);
        System.out.println("\nPrevious Contents in T0: " + m.getTileState("T0").getOccupance("u0").getClass().getSimpleName());
        System.out.println("nPrevious Contents in T1: " + m.getTileState("T1").getOccupance("u0"));
        c.execute();
        System.out.println("\nNew Contents in T0: " + m.getTileState("T0").getOccupance("u0"));
        System.out.println("nNew Contents in T1: " + m.getTileState("T1").getOccupance("u0").getClass().getSimpleName());
        
	}

}
