package application;

import application.models.commands.CommandGenerator;
import application.models.playerAsset.*;
import application.models.tileState.*;
import application.models.utility.*;
import application.views.*;

import java.util.ListIterator;
import java.util.Timer;
import java.util.TimerTask;

public class Game {

	private Player[] players;
	private Map map;
	private Player currentPlayer;
	private final int ROW = 15;
	private final int COL = 15;
	private TileGen T;
	private MainScreen mainScreen;
	
	public Game(){
		players = new Player[2];
		players[0] = new Player();
		players[1] = new Player();
		currentPlayer = players[0];
		T = new TileGen(ROW, COL);
		map = new Map(T.execute(), ROW, COL);

		CommandGenerator cGen = new CommandGenerator(players[0], map);
        cGen.generateCommand("IU_T1_colonist");
        cGen.generateCommand("IU_T5_explorer");
        cGen.generateCommand("IU_T14_melee");
        //cGen.generateCommand("IU_T8_ranged");

		for(int i = 0; i < 15; i++){
            for(int j = 0; j < 15; j++){
                System.out.println(i + " " + j);
                System.out.println(map.getTiles().get(("T"+ String.valueOf((j*15) + i))).getProperties());
            }
        }
        ListIterator unitIterator = currentPlayer.getUnitIterator();
		ListIterator structureIterator = currentPlayer.getStructureIterator();
		ListIterator armyIterator = currentPlayer.getArmyIterator();

		mainScreen = new MainScreen(map, unitIterator, structureIterator, armyIterator);
		mainScreen.generateMainScreen();
		mainScreen.showMainScreen();

		new Timer().schedule(new TimerTask(){
		    public void run(){
		        mainScreen.renderMainScreen();
            }
        }, 0, 250);
//		cGen.generateCommand("MVD_u1_S");
//		cGen.generateCommand("MVD_u3_S");
//		cGen.generateCommand("MVD_u2_S");
		cGen.generateCommand("NA_T100_u1_u2_u3");
		currentPlayer.endTurn();
		currentPlayer.beginTurn();
		currentPlayer.endTurn();
		currentPlayer.beginTurn();
		currentPlayer.endTurn();
		currentPlayer.beginTurn();
		currentPlayer.endTurn();
		currentPlayer.beginTurn();
//        System.out.println(map.getTileState("T4").getProperties());
        //cGen.generateCommand("IU_T8_ranged");
//        cGen.generateCommand("NA_T24_u1_u2_u3");

//        System.out.println(map.getTileState("T4").getProperties());
    }
	
	public void switchPlayers(){
		currentPlayer = (currentPlayer == players[0]) ? players[1] : players[0];
	}
}
