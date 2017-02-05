package application;

/* Standard library imports */
import java.util.ArrayList;

/* Main application packages */
import application.controllers.*;
import application.models.playerAsset.Player;
import application.models.tileState.Map;
import application.views.*;

/* Named as per Dave's specifications */
public class RunGame {
    public static void main(String[] args){
        Controller gameController = new Controller();
        View mainView = new View();

        ArrayList<Player> players = new ArrayList<Player>();
        Player currentPlayer;
        //Map gameMap = new Map();
    }
}
