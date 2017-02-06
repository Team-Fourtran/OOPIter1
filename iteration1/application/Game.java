package application;

import application.controllers.Controller;
import application.models.commands.CommandGenerator;
import application.models.playerAsset.*;
import application.models.tileState.*;
import application.models.utility.*;
import application.views.*;

/*
 * This class is for initializing the game and handling game flow at a high level (i.e., switching turns)
 */
public class Game {
>>>>>>> master

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
		
		// Create two players and generate map
		players = new Player[2];
		players[0] = new Player();
		players[1] = new Player();
		currentPlayer = players[0];
		T = new TileGen(ROW, COL);
		map = new Map(T.execute(), ROW, COL);

		// Generate a colonist and 2 explorer units for the two players
		CommandGenerator cGen0 = new CommandGenerator(players[0], map);
		cGen0.generateCommand("IU_T4_colonist");
		cGen0.generateCommand("IU_T5_explorer");
		cGen0.generateCommand("IU_T6_explorer");
        
		CommandGenerator cGen1 = new CommandGenerator(players[1], map);
		cGen1.generateCommand("IU_T10_colonist");
		cGen1.generateCommand("IU_T11_explorer");
		cGen1.generateCommand("IU_T12_explorer");

		for(int i = 0; i < 15; i++){
            for(int j = 0; j < 15; j++){
                System.out.println(i + " " + j);
                System.out.println(map.getTiles().get(("T"+ String.valueOf((j*15) + i))).getProperties());
            }
        }
<<<<<<< HEAD
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
=======
		
		ListIterator unitIterator = currentPlayer.getUnitIterator();
		ListIterator structureIterator = currentPlayer.getStructureIterator();

		// Startup main screen
		mainScreen = new MainScreen(map);
		mainScreen.prepareMainScreen();
		mainScreen.showMainScreen();

		try {
		    Thread.sleep(1000);
		} catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		}
		cGen0.generateCommand("IU_T8_colonist");
		cGen0.generateCommand("IU_T65_explorer");

		unitIterator = currentPlayer.getUnitIterator();
		structureIterator = currentPlayer.getStructureIterator();
		mainScreen.updateMainScreen(unitIterator, structureIterator);
	}

	// Retrieve the current player
	public Player getCurrentPlayer(){
		return currentPlayer;
>>>>>>> master
	}
	
	// Change the active player to the other one
	public void switchPlayers(){
		currentPlayer = (currentPlayer == players[0]) ? players[1] : players[0];
	}
}
