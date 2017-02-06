package application.models.commands;

import application.models.playerAsset.Player;
import application.models.tileState.Map;
import application.models.tileState.Occupance;

import java.util.ArrayList;

public class HealCommand extends ConcreteCommand{
    private String structureID;

    HealCommand(Player _p, Map _m) {
        super(_p, _m);
    }

    public void doInitialize(String ... strings){
        structureID = strings[1];
        getPlayer().notify(structureID, this);
    }

    @Override
    public void execute(){
        Map m = getMap();
        Player p = getPlayer();
        ArrayList<Occupance> occupances = m.getTileState(p.getPosition(structureID)).getOccupance();
        for(Occupance _o : occupances){
            _o.healPlayerAssets();
        }
    }
}
