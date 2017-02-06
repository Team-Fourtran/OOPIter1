package application.models.commands;

import application.models.playerAsset.Player;
import application.models.tileState.Directions;
import application.models.tileState.Map;
/*
 * The discrete movement for an asset to move from one tile to an adjacent one
 */
public class MoveDirectionCommand extends ConcreteCommand{
    private Directions direction;
    private String degreesDirection;
    private String assetID;

    MoveDirectionCommand(Player _p, Map _m){
        super(_p, _m);
    }

    /*
     * Specify the assetID to move and the direction (in degrees) to move.
     * Notify the player that this assetID has this command
     */
    @Override
    public void doInitialize(String... strings) {
        assetID = strings[1];
        degreesDirection = strings[2];

        for (Directions d : Directions.values()) {
        	if (Integer.parseInt(degreesDirection) == d.getValue()) {
        		direction = d;
        	}
        }
        getPlayer().notify(assetID, this);
    }

    // Move the occupance from the start tile to the tile in the proper direction
    @Override
    public void execute() {
        Map map = getMap();
        Player player = getPlayer();
        System.out.println("\nMoving " + assetID + " direction " + direction);
        map.getTileState(player.getPosition(assetID)).moveOccupance(assetID, direction);
    }

    @Override
    public double getTurns() {
        return 0.33;
    }

    @Override
    protected void setPacking(){
        needsUnpacked = false;
    }
}
