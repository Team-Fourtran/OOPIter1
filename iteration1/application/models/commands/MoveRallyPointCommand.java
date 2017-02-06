package application.models.commands;

import application.models.playerAsset.Player;
import application.models.tileState.AssetOccupance;
import application.models.tileState.Map;
import application.models.tileState.Occupance;

import java.util.ArrayList;

public class MoveRallyPointCommand extends ConcreteCommand{
    private String armyID;
    private String destinationTileID;
    private String currentTileID;
    private ArrayList<String> unitIDList = new ArrayList<>();

    MoveRallyPointCommand(Player _p, Map _m) {
        super(_p, _m);
    }

    @Override
    public void doInitialize(String ... strings){
        Player p = getPlayer();
        Map m = getMap();
        currentTileID = p.getPosition(armyID);
        armyID = strings[1];
        destinationTileID = strings[2];
        unitIDList = getPlayer().getUnitIDs(armyID);

        Occupance o = m.getTileState(currentTileID).getOccupance(armyID);
        m.getTileState(currentTileID).removeOccupance(armyID);
        m.getTileState(destinationTileID).addOccupance(o);
        p.setRallyPoint(armyID, destinationTileID);
        this.unpack();
    }
    @Override
    protected void setPacking(){
        needsUnpacked = true;
    }
    @Override
    public void unpack(){
        for(String assetID : unitIDList){
            Command cmd = new MoveAssetCommand(getPlayer(), getMap());
            cmd.initialize("MV", assetID, currentTileID, destinationTileID);
        }
    }
}
