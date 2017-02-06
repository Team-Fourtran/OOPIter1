package application.models.commands;

import application.models.playerAsset.Player;
import application.models.tileState.Map;
import application.models.tileState.Occupance;

import java.util.ArrayList;
/*
 * This command allows one to move an army's rally point i.e. get the army to move
 */
public class MoveRallyPointCommand extends ConcreteCommand{
    private String armyID;
    private String destinationTileID;
    private String currentArmyPosition;
    private ArrayList<String> unitIDList = new ArrayList<>();

    MoveRallyPointCommand(Player _p, Map _m) {
        super(_p, _m);
    }

    /*
     * Specify the armyID, destination tile, the current army position, and the list of this army's units
     */
    @Override
    public void doInitialize(String ... strings){
        Player p = getPlayer();
        Map m = getMap();
        armyID = strings[1];
        destinationTileID = strings[2];
        currentArmyPosition = p.getPosition(armyID);
        unitIDList = getPlayer().getUnitIDs(armyID);

        // Add the occupance containing the armyID to the destination tile (the rally point)
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
    
    // Discrete commands. Tell each unit in the army to start moving towards the destination tile
    @Override
    public void unpack(){
        for(String assetID : unitIDList){
            Command cmd = new MoveAssetCommand(getPlayer(), getMap());
            cmd.initialize("MV", assetID, getPlayer().getPosition(assetID), destinationTileID);
        }
    }
}
