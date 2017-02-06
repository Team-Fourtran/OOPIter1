package application.models.commands;

import application.models.playerAsset.Player;
import application.models.tileState.Map;
/*
 * This command is for moving an asset from a start tile to a destination tile.
 * Consists of discrete movements
 */
public class MoveAssetCommand extends ConcreteCommand{
    private String startTileID;
    private String destinationTileID;
    private String assetID;


    MoveAssetCommand(Player _p, Map _m){
        super(_p, _m);
    }

    // Specify asset to move, and start/destinationID
    @Override
    public void doInitialize(String... strings) {
        if(strings.length != 4){
            System.out.println("Not enough arguments for initialize");
        }
        assetID = strings[1];
        startTileID = strings[2];
        destinationTileID = strings[3];
        this.unpack();
    }
    
    // This needs unpacking since it consists of discrete movements
    @Override
    protected void setPacking(){
        needsUnpacked = true;
    }

    /*
     * Retrieve the optimal path from start to end. For each direction given by the path finding algorithm
     * issue another command to move in that direction
     */
    @Override
    public void unpack() {
        String path = getMap().generatePath(startTileID, destinationTileID);
        //e.g., 90_90_180
        if (path.equals("")){
            return; //No movement necessary!
        }
        String[] directionsArray = path.split("[_]");
        for(int i = 0; i < directionsArray.length; i++){
            Command cmd = new MoveDirectionCommand(getPlayer(), getMap());
            cmd.initialize("MVD", assetID, directionsArray[i]);
        }
        
    }
}
