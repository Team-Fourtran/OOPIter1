package application.models.commands;

import application.models.playerAsset.Player;
import application.models.tileState.Map;

public class NullCommand extends ConcreteCommand{
    NullCommand(Player _p, Map _m){
        super(_p, _m);
    }
}
