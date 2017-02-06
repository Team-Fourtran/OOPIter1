package tests;

import java.awt.List;
import java.util.ArrayList;
import java.util.Iterator;

import application.models.commands.Command;
import application.models.commands.CommandGenerator;
import application.models.playerAsset.Army;
import application.models.playerAsset.Player;
import application.models.playerAsset.Unit;
import application.models.tileState.AssetOccupance;
import application.models.tileState.Map;
import application.models.tileState.Occupance;
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
        
        // Adding unit to tile T0
//        Command c = cGen.generateCommand("IU_0_T0_colonist").get(0);
//        c.execute();
//
//        System.out.println(m.getTileState("T0").getProperties());
//
//        // Create an army containing that colonist unit
//        System.out.println("\nCreate army");
//        ArrayList<String> units = new ArrayList<String>();
//        units.add("u1");
//        Army a = p.formArmy(units, "T0");
//
//        ArrayList<Occupance> o = m.getTileState("T0").getOccupance();
//
//        Iterator<Occupance> i = o.iterator();
//        while(i.hasNext()) {
//        	Occupance oc = i.next();
//        	if (oc.getAssetID().equals("u1"));
//        	i.remove();
//        }
//
//    	Occupance arm = new AssetOccupance(a);
//    	m.getTileState("T0").addOccupance(arm);
//
//
//        System.out.println(m.getTileState("T0").getProperties() + "\n");
//
//        // New Structure command
//
//        c = cGen.generateCommand("NS_a1").get(0);
//        c.execute();
//
//        System.out.println(m.getTileState("T0").getProperties());
	}

}
