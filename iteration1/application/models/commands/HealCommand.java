package application.models.commands;

import application.models.playerAsset.Player;
import application.models.tileState.Map;
import application.models.tileState.Occupance;

import java.util.ArrayList;
/*
 * This command is for telling a structure to heal all units on its tile
 */
public class HealCommand extends ConcreteCommand{
    private String structureID;

    HealCommand(Player _p, Map _m) {
        super(_p, _m);
    }

    // Set argument for structureID. Notify player that a command to this structure has been issued, and hand it that command
    public void doInitialize(String ... strings){
        structureID = strings[1];
        getPlayer().notify(structureID, this);
    }

    // What to do when this command is executed
    @Override
    public void execute(){
        Map m = getMap();
        Player p = getPlayer();
        // Get occupance where this structure is located
        ArrayList<Occupance> occupances = m.getTileState(p.getPosition(structureID)).getOccupance();
        // Heal each one of the assets on this tile
        for(Occupance _o : occupances){
            _o.healPlayerAssets();
        }
    }
}
