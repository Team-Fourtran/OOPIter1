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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.HashMap;

public class TestMessageGenerator {
    static MainScreen mainView;
    static Controller gameController;

    public static void main(String[] args){
        System.out.println("Starting game");
        //mainView = new MainScreen();
        mainView.prepareMainScreen();
        mainView.showMainScreen();

        Player p = new Player();


        // Create a colonist unit
        int length = 4 , width = 4;
        TileGen T = new TileGen(length, width);
        Map m = new Map(T.execute(), length, width);
        m.printOut();

        CommandGenerator cGen = new CommandGenerator(p, m);

        // Adding unit to tile T0
//        Command c = cGen.generateCommand("IU_0_T0_colonist").get(0);
//        c.execute();

        System.out.println(m.getTileState("T0").getProperties());

        // Create an army containing that colonist unit
        System.out.println("\nCreate army");
        ArrayList<String> units = new ArrayList<String>();
        units.add("u1");
        Army a = p.formArmy(units, "T0");

        ArrayList<Occupance> o = m.getTileState("T0").getOccupance();

        Iterator<Occupance> i = o.iterator();
        while(i.hasNext()) {
            Occupance oc = i.next();
            if (oc.getAssetID().equals("u1"));
            i.remove();
        }

        Occupance arm = new AssetOccupance(a);
        m.getTileState("T0").addOccupance(arm);


        System.out.println(m.getTileState("T0").getProperties() + "\n");

        // New Structure command

//        c = cGen.generateCommand("NS_a1").get(0);
 //       c.execute();

        System.out.println(m.getTileState("T0").getProperties());

        HashMap<String, Iterator> assetIterators = new HashMap<String, Iterator>();
        assetIterators.put("Army", p.getArmyIterator());
        assetIterators.put("Unit", p.getUnitIterator());
        assetIterators.put("Structure", p.getStructureIterator());

        gameController = new Controller(mainView.getKeyInformer(),assetIterators);

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
