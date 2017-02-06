package application.models.commands;

import application.models.playerAsset.Player;
import application.models.tileState.Map;

import java.util.ArrayList;

public class MoveAssetCommand extends ConcreteCommand{
    private String startTileID;
    private String destinationTileID;
    private String assetID;


    MoveAssetCommand(Player _p, Map _m){
        super(_p, _m);
    }

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
    @Override
    protected void setPacking(){
        needsUnpacked = true;
    }

    @Override
    public void unpack(){
        String path = getMap().generatePath(startTileID, destinationTileID);
        //e.g., 90_90_180
        String[] directionsArray = path.split("[_]");
        for(int i = 0; i < directionsArray.length; i++){
            Command cmd = new MoveDirectionCommand(getPlayer(), getMap());
            cmd.initialize("MVD", assetID, directionsArray[i]);
        }
    }
}
