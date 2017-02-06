package tests;

import application.controllers.*;
import application.models.commands.Command;
import application.models.commands.CommandGenerator;
import application.models.playerAsset.Army;
import application.models.tileState.AssetOccupance;
import application.models.tileState.Map;
import application.models.tileState.Occupance;
import application.models.utility.TileGen;
import application.views.MainScreen;
import application.views.MainScreen;
import application.models.playerAsset.Player;

import javax.swing.text.html.HTMLDocument;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.HashMap;
import java.util.ListIterator;

public class TestMessageGenerator {
    static MainScreen mainView;
    static Controller gameController;

    public static void main(String[] args){
        Player p = new Player();

        int length = 4 , width = 4;
        TileGen T = new TileGen(length, width);
        Map m = new Map(T.execute(), length, width);
        m.printOut();

        CommandGenerator cGen = new CommandGenerator(p, m);

        /*
         * Populating tile T0 & T1
         */
        
        // Adding colonist unit & explorer unit to tile T0
        Command c = cGen.generateCommand("IU_T0_colonist").get(0);
        c.execute();
        c = cGen.generateCommand("IU_T0_explorer").get(0);
        c.execute();
        
        // Adding two more melee create two armies
        c = cGen.generateCommand("IU_T0_colonist").get(0);
        c.execute();
        c = cGen.generateCommand("IU_T1_colonist").get(0);
        c.execute();
        
        ArrayList<String> units1 = new ArrayList<String>();
        units1.add("u3");
        Army a1 = p.formArmy(units1, "T0");

        ArrayList<Occupance> o1 = m.getTileState("T0").getOccupance();
        
        Iterator<Occupance> i1 = o1.iterator();
        while(i1.hasNext()) {
        	Occupance oc = i1.next();
        	if (oc.getAssetID().equals("u3")) {
            	i1.remove();
            	break;
        	};
        }
        
    	Occupance arm1 = new AssetOccupance(a1);
    	m.getTileState("T0").addOccupance(arm1);
    	
        ArrayList<String> units2 = new ArrayList<String>();
        units2.add("u4");
        Army a2 = p.formArmy(units2, "T1");

        ArrayList<Occupance> o2 = m.getTileState("T1").getOccupance();
        
        Iterator<Occupance> i2 = o2.iterator();
        while(i2.hasNext()) {
        	Occupance oc = i2.next();
        	if (oc.getAssetID().equals("u4")) {
            	i2.remove();
            	break;
        	};
        }
        
    	Occupance arm2 = new AssetOccupance(a2);
    	m.getTileState("T1").addOccupance(arm2);
    	
        // Adding two colonist units to T0, T1 => armies => structures
        c = cGen.generateCommand("IU_T2_colonist").get(0);
        c.execute();
        c = cGen.generateCommand("IU_T3_colonist").get(0);
        c.execute();
        
        ArrayList<String> units3 = new ArrayList<String>();
        units3.add("u5");
        Army a3 = p.formArmy(units3, "T2");
        
        ArrayList<Occupance> o3 = m.getTileState("T2").getOccupance();
        
        Iterator<Occupance> i3 = o3.iterator();
        while(i3.hasNext()) {
        	Occupance oc = i3.next();
        	if (oc.getAssetID().equals("u5")) {
            	i3.remove();
            	break;
        	};
        }
        
    	Occupance arm3 = new AssetOccupance(a3);
    	m.getTileState("T2").addOccupance(arm3);
        
        c = cGen.generateCommand("NS_a3").get(0);
        c.execute();
        
        ArrayList<String> units4 = new ArrayList<String>();
        units4.add("u6");
        Army a4 = p.formArmy(units4, "T3");
        
        ArrayList<Occupance> o4 = m.getTileState("T3").getOccupance();
        
        Iterator<Occupance> i4 = o4.iterator();
        while(i4.hasNext()) {
        	Occupance oc = i4.next();
        	if (oc.getAssetID().equals("u6")) {
            	i4.remove();
            	break;
        	};
        }
        
    	Occupance arm4 = new AssetOccupance(a4);
    	m.getTileState("T3").addOccupance(arm4);
        
        c = cGen.generateCommand("NS_a4").get(0);
        c.execute();
        
        System.out.println(m.getTileState("T0").getProperties());
        System.out.println(m.getTileState("T1").getProperties());
        System.out.println(m.getTileState("T2").getProperties());
        System.out.println(m.getTileState("T3").getProperties());

        ListIterator unitIterator = p.getUnitIterator();
        ListIterator armyIterator = p.getArmyIterator();
        ListIterator structureIterator = p.getStructureIterator();

        HashMap<String, ListIterator> iters = new HashMap<String, ListIterator>();
        iters.put("unit",unitIterator);
        iters.put("army",armyIterator);
        iters.put("structure",structureIterator);

        mainView = new MainScreen();
        mainView.prepareMainScreen();
        mainView.showMainScreen();

        gameController = new Controller(mainView.getKeyInformer(),iters);


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
//            Occupance oc = i.next();
//            if (oc.getAssetID().equals("u1"));
//            i.remove();
//        }
//
//        Occupance arm = new AssetOccupance(a);
//        m.getTileState("T0").addOccupance(arm);
//
//        // New Structure command
//
//        c = cGen.generateCommand("NS_a1").get(0);
//        c.execute();
//
//        System.out.println(m.getTileState("T0").getProperties());
//
//        HashMap<String, Iterator> assetIterators = new HashMap<String, Iterator>();
//        assetIterators.put("Army", p.getArmyIterator());
//        assetIterators.put("Unit", p.getUnitIterator());
//        assetIterators.put("Structure", p.getStructureIterator());
//
//        gameController = new Controller(mainView.getKeyInformer(),assetIterators);
//
    }

    /*
    private static void switchActivePlayer(){
        currentPlayer = playerList.get(
                (playerList.indexOf(currentPlayer)+1) % 2
        );
        HashMap<String, Iterator> updatedIterators = new HashMap<String, Iterator>();
        updatedIterators.put("Army", currentPlayer.getArmyIterator());
        updatedIterators.put("Unit", currentPlayer.getUnitIterator());
        updatedIterators.put("Structure", currentPlayer.getStructureIterator());
        gameController.updateIterators(updatedIterators);
    }
    */
}
