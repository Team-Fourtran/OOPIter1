package application.models.commands;

import application.models.playerAsset.Player;
import application.models.tileState.Map;

public class HealUnitCommand extends ConcreteCommand {
    private String structureID;
    private String unitID;

	HealUnitCommand(Player _p, Map _m) {
		super(_p, _m);
	}

    public void doInitialize(String ... strings){
        structureID = strings[1];
        unitID = strings[2];
    }
	
}
