package application.models.commands;

import java.util.ArrayList;

import application.models.playerAsset.Player;
import application.models.tileState.AssetOccupance;
import application.models.tileState.Map;
import application.models.tileState.Occupance;
/*
 * Create a new army command
 */
public class NewArmyCommand extends ConcreteCommand{
    private String destinationTileID;
    private ArrayList<String> unitIDList;

    NewArmyCommand(Player _p, Map _m) {
        super(_p, _m);
    }

    // Specify the army's rally point (destinationTileID)
    @Override
    public void doInitialize(String... strings){
        destinationTileID = strings[1];
        if (strings.length < 2){
            System.out.println("Not enough arguments to create army!");
            return;
        }
        //Populates an array of unit IDs comprising the army
        unitIDList = new ArrayList<>(strings.length - 2);//String[strings.length - 2];
        for (int i = 2; i < strings.length; i++){
            unitIDList.add(strings[i]);
        }
        Occupance _o = new AssetOccupance(getPlayer().formArmy(unitIDList, destinationTileID));
        getMap().getTileState(destinationTileID).addOccupance(_o);
        this.unpack();
    }

    @Override
    public void setPacking(){
        needsUnpacked = true;
    }

    @Override
    public void unpack(){
        //Creates a moveAssetCommand for each unit that has to move...
        for(String assetID : unitIDList){
            Command cmd = new MoveAssetCommand(getPlayer(), getMap());
            String startTileID = getPlayer().getPosition(assetID);
            cmd.initialize("MV", assetID, startTileID, destinationTileID);
        }
    }
}
