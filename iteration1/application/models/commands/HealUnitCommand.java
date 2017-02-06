package application.models.commands;

import application.models.playerAsset.Player;
import application.models.tileState.Map;

public class HealUnitCommand extends ConcreteCommand {
    private String structureID;

	HealUnitCommand(Player _p, Map _m) {
		super(_p, _m);
	}

	@Override
    public void doInitialize(String ... strings){
        if(strings.length != 2){
            System.out.println("Not enough arguments for initialize");
        }
        structureID = strings[1];
        
        getPlayer().notify(structureID, this);
    }
	
	@Override
	public void execute() {
		Map map = getMap();
		Player player = getPlayer();
		// find the tilestate that has the occupance of this struture id
		// then pass heal units an ArrayList of all of those assets in the TS's occupances
		System.out.println(map.getTileState(player.getPosition(structureID)));
		player.healUnits(structureID);
	}
	
	@Override
	public double getTurns() {
		return 1;
	}
	
}
