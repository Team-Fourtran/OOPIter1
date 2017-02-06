package application;

import application.controllers.Controller;
import application.models.commands.CommandGenerator;
import application.models.playerAsset.*;
import application.models.tileState.*;
import application.models.utility.*;
import application.views.*;

import java.util.HashMap;
import java.util.ListIterator;

public class Game {
	private Controller controller;
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
		mainScreen = new MainScreen(map,currentPlayer.getUnitIterator());
		mainScreen.prepareMainScreen();
		mainScreen.showMainScreen();

		HashMap<String, ListIterator> iterators = new HashMap<>();
		iterators.put("unit",currentPlayer.getUnitIterator());
		iterators.put("army",currentPlayer.getArmyIterator());
		iterators.put("structure",currentPlayer.getStructureIterator());

/* DEBUG: Prints all PlayerAssets the game is starting with
		System.out.println("\nUnits:");
        while(iterators.get("unit").hasPrevious()){
            iterators.get("unit").previous();
        }
        while(iterators.get("unit").hasNext()){
            Unit u = (Unit) iterators.get("unit").next();
            System.out.println("\t" + u.getID() + ": " + u.getType());
        }

        System.out.println("\nArmies:");
        while(iterators.get("army").hasPrevious()){
            iterators.get("army").previous();
        }
        while(iterators.get("army").hasNext()){
            Army u = (Army) iterators.get("army").next();
            System.out.println("\t" + u.getID() + ": " + u.getType());
        }

        System.out.println("\nStructures:");
        while(iterators.get("structure").hasPrevious()){
            iterators.get("structure").previous();
        }
        while(iterators.get("structure").hasNext()){
            Structure u = (Structure) iterators.get("structure").next();
            System.out.println("\t" + u.getID() + ": " + u.getType());
        }
*/

		controller = new Controller(mainScreen,iterators,cGen);
	}
	
	public void switchPlayers(){
		currentPlayer = (currentPlayer == players[0]) ? players[1] : players[0];
	}
}
