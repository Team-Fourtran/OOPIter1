package tests;

import application.controllers.*;
import application.views.MainScreen;
import application.views.MainScreen;
import application.models.playerAsset.Player;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.HashMap;

public class TestMessageGenerator {
    static MainScreen mainView;
    static Controller gameController;
    static ArrayList<Player> playerList;
    static Player currentPlayer;

    public static void main(String[] args){
        System.out.println("Starting game");
        mainView = new MainScreen();
        mainView.showMainScreen();
/*
        playerList = new ArrayList<Player>();

        playerList.add(new Player());   //Player 1
        playerList.add(new Player());   //Player 2

        currentPlayer = playerList.get(0);

        HashMap<String, Iterator> assetIterators = new HashMap<String, Iterator>();
        assetIterators.put("Army", currentPlayer.getArmyIterator());
        assetIterators.put("Unit", currentPlayer.getUnitIterator());
        assetIterators.put("Structure", currentPlayer.getStructureIterator());

        //gameController = new Controller(mainView.getKeyPressListener(),assetIterators);
*/
    }

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
}
