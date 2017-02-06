package application.models.commands;

import application.models.playerAsset.Player;
import application.models.tileState.AssetOccupance;
import application.models.tileState.Map;
import application.models.tileState.Occupance;

/*
 * Command intended for the initial units (2 explorers, 1 colonist) for a given Player
 */
public class InitialUnitsCommand extends ConcreteCommand {
	private String destinationTileID;
	private String unitType;

	InitialUnitsCommand(Player _p, Map _m) {
		super(_p, _m);
	}

	public void doInitialize(String... strings) {
		destinationTileID = strings[1];
		unitType = strings[2];
		getPlayer().notify(this);
	}

	@Override
	public void execute() {
	    Player p = getPlayer();
	    Occupance _o = new AssetOccupance(p.createInitialUnit(destinationTileID, unitType));
		getMap().getTileState(destinationTileID).addOccupance(_o);
	}
}
