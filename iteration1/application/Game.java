package application;

import application.models.commands.CommandGenerator;
import application.models.playerAsset.*;
import application.models.tileState.*;
import application.models.utility.*;
import application.views.*;

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
		cGen.generateCommand("IU_T4_colonist");
		cGen.generateCommand("IU_T5_explorer");
		cGen.generateCommand("IU_T6_melee");
        cGen.generateCommand("IU_T6_ranged");

		for(int i = 0; i < 15; i++){
            for(int j = 0; j < 15; j++){
                System.out.println(i + " " + j);
                System.out.println(map.getTiles().get(("T"+ String.valueOf((j*15) + i))).getProperties());
            }
        }
//		mainScreen = new MainScreen(map);
//		mainScreen.prepareMainScreen();
//		mainScreen.showMainScreen();
	}
	
	public void switchPlayers(){
		currentPlayer = (currentPlayer == players[0]) ? players[1] : players[0];
	}
}
