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

    }
    @Override
    protected void setPacking(){
        needsUnpacked = true;
    }

    @Override
    public ArrayList<Command> unpack(){
        String path = getMap().generatePath(startTileID, destinationTileID);
        //e.g., N_N_NW_NW_W
        String[] directionsArray = path.split("[_]");
        ArrayList<Command> cmdList = new ArrayList<>(directionsArray.length);
        for(int i = 0; i < directionsArray.length; i++){
            cmdList.add(new MoveDirectionCommand(getPlayer(), getMap()));
            cmdList.get(i).initialize("MVD", assetID, directionsArray[i]);
        }
        return cmdList;
    }
}
