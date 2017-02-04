package application.models.commands;

import application.models.playerAsset.Player;
import application.models.playerAsset.Structure;
import application.models.tileState.Map;

public interface Command {
    void execute(Map m, Player p);
}

abstract class concreteCommand implements Command {
    protected int turnsToExcecute; //Can be private with final set methods?
    protected int turnsRemaining;

}

class newStructureCommand extends concreteCommand{
    private String unitID;

    public newStructureCommand(String unitID){
        turnsToExcecute = 1;
        this.unitID = unitID;
    }

    @Override
    public void execute(Map m, Player p) {
        if(p.canCreateStructure(unitID)){
            Structure s = p.createStructure(unitID);
            m.addAsset(s.getTileID(), p.createStructure(unitID));
        }
    }
}

class moveUnitCommand extends concreteCommand{

    public moveUnitCommand(String unitID, String destinationTileID){

    }

    @Override
    public void execute(Map m, Player p) {

    }
}

class nullCommand extends concreteCommand{
    public nullCommand(){

    }
    @Override
    public void execute(Map m, Player p) {
        return;
    }
}