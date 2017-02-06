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
    	int length = 4 , width = 4;
        TileGen T = new TileGen(length, width);
        Map m = new Map(T.execute(), length, width);
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
//        cGen.generateCommand("MRP_a1_T3"); //Might have to clear associated queues???
        System.out.println("-----AFTER-----");
        System.out.println("u1: " + p.getPosition("u1"));
        System.out.println("u2: " + p.getPosition("u2"));
        System.out.println("u3: " + p.getPosition("u3"));
        p.endTurn();
        p.beginTurn();
        System.out.println("-----AFTER2-----");
        System.out.println("u1: " + p.getPosition("u1"));
        System.out.println("u2: " + p.getPosition("u2"));
        System.out.println("u3: " + p.getPosition("u3"));
        p.endTurn();
        p.beginTurn();
        System.out.println("-----AFTER3-----");
        System.out.println("u1: " + p.getPosition("u1"));
        System.out.println("u2: " + p.getPosition("u2"));
        System.out.println("u3: " + p.getPosition("u3"));
        p.endTurn();
        p.beginTurn();
        System.out.println("-----AFTER4-----");
        System.out.println("u1: " + p.getPosition("u1"));
        System.out.println("u2: " + p.getPosition("u2"));
        System.out.println("u3: " + p.getPosition("u3"));
        p.endTurn();
        p.beginTurn();
        System.out.println("-----AFTER5-----");
        System.out.println("u1: " + p.getPosition("u1"));
        System.out.println("u2: " + p.getPosition("u2"));
        System.out.println("u3: " + p.getPosition("u3"));
	}

}