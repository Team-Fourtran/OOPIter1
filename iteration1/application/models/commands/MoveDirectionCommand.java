package application.models.commands;

import application.models.playerAsset.Player;
import application.models.tileState.Directions;
import application.models.tileState.Map;

public class MoveDirectionCommand extends ConcreteCommand{
    private Directions direction;
    private String degreesDirection;
    private String assetID;

    MoveDirectionCommand(Player _p, Map _m){
        super(_p, _m);
    }

    @Override
    public void doInitialize(String... strings) {
        assetID = strings[1];
        degreesDirection = strings[2];

        for (Directions d : Directions.values()) {
        	if (Integer.parseInt(degreesDirection) == d.getValue()) {
        		direction = d;
        	}
        }
    }

    @Override
    public void execute() {
        Map map = getMap();
        Player player = getPlayer();

        System.out.println("\nMoving " + assetID + " direction " + direction);
        System.out.println(player.getPosition(assetID));
        map.getTileState(player.getPosition(assetID)).moveOccupance(assetID, direction);

    }
    @Override
    protected void setPacking(){
        needsUnpacked = false;
    }
}
