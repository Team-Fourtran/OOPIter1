package application.models.commands;

import application.models.playerAsset.Player;
import application.models.tileState.Map;

import java.util.Iterator;

public class MoveRallyPointCommand extends ConcreteCommand{
    private String armyID;
    private String destinationTileID;

    MoveRallyPointCommand(Player _p, Map _m) {
        super(_p, _m);
    }

    @Override
    public void doInitialize(String ... strings){
        armyID = strings[1];
        destinationTileID = strings[2];


        this.unpack();
    }
    @Override
    protected void setPacking(){
        needsUnpacked = true;
    }
    @Override
    public void unpack(){
        //getMap().getTileState(destinationTileID)
    }
}
