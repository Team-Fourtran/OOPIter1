package application.models.playerAsset;

import application.models.commands.Command;
import java.util.*;

public class Structure extends PlayerAsset{


    int productionRate;
    int commandCount;
    Queue<Command> commandQueue = new LinkedList<>();

    public Structure(){

        commandCount = 0;
        offDamage = 75;
        defDamage = 75;
        armor = 150;
        productionRate = 2;
        maxHealth = currentHealth = 200;
        upkeep = 1;
        
    }

    //heal unit on same tile as structure
    public void healUnit(Unit u){
        u.heal(50);
    }

    //execute next command in queue
    //if multi-turn command, wait appropriate amount of turns
    public void executeCommand(){
            commandCount++;
            if (equal(commandQueue.peek().getTurns(), commandCount)){
                commandQueue.peek().execute();
                commandQueue.remove();
                commandCount = 0;
            }

    }

    //helper function for execute
    public boolean equal(double d, int i){
        double n = d-i;
        if (n < 0.000001)
            return true;
        return false;
    }

}
