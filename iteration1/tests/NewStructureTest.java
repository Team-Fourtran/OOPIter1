package tests;

import application.models.commands.CommandGenerator;
import application.models.playerAsset.Player;
import application.models.tileState.Map;
import application.models.utility.TileGen;

public class NewStructureTest {
	public static void main(String[] args) {

		// Create a colonist unit
    	int length = 5 , width = 5;
        TileGen T = new TileGen(length, width);
        Map m = new Map(T.execute(), length, width);
        m.printOut();
        Player p = new Player();
        

        CommandGenerator cGen = new CommandGenerator(p, m);

        cGen.generateCommand("IU_T0_colonist");
        cGen.generateCommand("IU_T1_colonist");
        cGen.generateCommand("IU_T2_colonist");
        System.out.println("-----BEFORE-----");
        System.out.println("u1: " + p.getPosition("u1"));
        System.out.println("u2: " + p.getPosition("u2"));
        System.out.println("u3: " + p.getPosition("u3"));
        cGen.generateCommand("NA_T24_u1_u2_u3");
        System.out.println("-----AFTER-----");
        System.out.println("u1: " + p.getPosition("u1"));
        System.out.println("u2: " + p.getPosition("u2"));
        System.out.println("u3: " + p.getPosition("u3"));
 //       cGen.generateCommand("MRP_a1_T0"); //Might have to clear associated queues???
        p.endTurn();
        p.beginTurn();
        System.out.println("-----AFTER2-----");
        System.out.println("u1: " + p.getPosition("u1"));
        System.out.println("u2: " + p.getPosition("u2"));
        System.out.println("u3: " + p.getPosition("u3"));
        cGen.generateCommand("NS_");
        p.endTurn();
        p.beginTurn();
        System.out.println("-----AFTER2-----");
        System.out.println("u1: " + p.getPosition("u1"));
        System.out.println("u2: " + p.getPosition("u2"));
        System.out.println("u3: " + p.getPosition("u3"));

	}

}
