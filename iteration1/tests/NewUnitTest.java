package tests;

import java.util.ArrayList;
import java.util.Iterator;

import application.models.commands.Command;
import application.models.commands.CommandGenerator;
import application.models.playerAsset.Army;
import application.models.playerAsset.Player;
import application.models.tileState.AssetOccupance;
import application.models.tileState.Map;
import application.models.tileState.Occupance;
import application.models.utility.TileGen;

/*
 * This is for testing Unit production from structures
 */
public class NewUnitTest {
	public static void main(String[] args) {
//		// Need to obtain a structure
//
//		// Create a colonist unit
//    	int length = 4 , width = 4;
//        TileGen T = new TileGen(length, width);
//        Map m = new Map(T.execute(), length, width);
//        m.printOut();
//        Player p = new Player();
//
//        CommandGenerator cGen = new CommandGenerator(p, m);
//
//        // Adding unit to tile T0
////        Command c = cGen.generateCommand("IU_0_T0_colonist").get;
////        c.execute();
//
//        // Create an army containing that colonist unit
//        System.out.println("\nCreate army");
//        ArrayList<String> units = new ArrayList<String>();
//        units.add("u1");
//        Army a = p.formArmy(units, "T0");
//        p.freeFromSuffering("u1");
//
//        ArrayList<Occupance> o = m.getTileState("T0").getOccupance();
//
//        // Remove occupance for unit
//        Iterator<Occupance> i = o.iterator();
//        while(i.hasNext()) {
//        	Occupance oc = i.next();
//        	if (oc.getAssetID().equals("u1"));
//        	i.remove();
//        }
//
//        // Add occupance for army
//    	Occupance arm = new AssetOccupance(a);
//    	m.getTileState("T0").addOccupance(arm);
//
//        // New Structure command
//        c = cGen.generateCommand("NS_a1").get(0);
//        c.execute();
//
//        System.out.println(m.getTileState("T0").getProperties());
//
//        // Apply NewUnitCommand
//		c = cGen.generateCommand("NU_s1_explorer").get(0);
//		c.execute();
//
//		System.out.println(m.getTileState("T0").getProperties());
//
//		c.execute();
//
//		System.out.println(m.getTileState("T0").getProperties());
	}
}
