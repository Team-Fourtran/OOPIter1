package application.models.commands;

import application.models.playerAsset.Player;
import application.models.tileState.Map;
import application.models.tileState.Occupance;

import java.util.ArrayList;

public class MoveRallyPointCommand extends ConcreteCommand{
    private String armyID;
    private String destinationTileID;
    private String currentArmyPosition;
    private ArrayList<String> unitIDList = new ArrayList<>();

    MoveRallyPointCommand(Player _p, Map _m) {
        super(_p, _m);
    }

    @Override
    public void doInitialize(String ... strings){
        Player p = getPlayer();
        Map m = getMap();
        armyID = strings[1];
        destinationTileID = strings[2];
        currentArmyPosition = p.getPosition(armyID);
        unitIDList = getPlayer().getUnitIDs(armyID);

        Occupance o = m.getTileState(currentArmyPosition).getOccupance(armyID);
        m.getTileState(currentArmyPosition).removeOccupance(armyID);
        m.getTileState(destinationTileID).addOccupance(o);
        p.setRallyPoint(armyID, destinationTileID);
        p.resetArmyUnitQueue(armyID);
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
            cmd.initialize("MV", assetID, getPlayer().getPosition(assetID), destinationTileID);
        }
    }
}
