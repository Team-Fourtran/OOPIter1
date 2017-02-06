package application.models.commands;

import application.models.playerAsset.Player;
import application.models.tileState.AssetOccupance;
import application.models.tileState.Map;
import application.models.tileState.Occupance;

/*
 * Command intended for the initial units for a given Player (without a structure)
 */
public class InitialUnitsCommand extends ConcreteCommand {
	private String destinationTileID;
	private String unitType;

	InitialUnitsCommand(Player _p, Map _m) {
		super(_p, _m);
	}

	/*
	 * Takes in the destination tile where the unit shall be located
	 * Also takes in the type of unit to create
	 */
	public void doInitialize(String... strings) {
		destinationTileID = strings[1];
		unitType = strings[2];
		getPlayer().notify(this);
	}

	@Override
	public void execute() {
	    Player p = getPlayer();
	    // Create a new Occupance on the tile with this unitType, add it to the map
	    Occupance _o = new AssetOccupance(p.createInitialUnit(destinationTileID, unitType));
		getMap().getTileState(destinationTileID).addOccupance(_o);
	}
}
