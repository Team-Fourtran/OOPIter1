package application.models.commands;

import application.models.playerAsset.Player;
import application.models.tileState.AssetOccupance;
import application.models.tileState.Map;
import application.models.tileState.Occupance;
/*
 * Command to create a new Structure from an army
 */
public class NewStructureCommand extends ConcreteCommand{
    private String assetID;
    //private String tileID; //PENDING CONTROLLER IMPLEMENTATION

    NewStructureCommand(Player _p, Map _m){
        super(_p, _m);
    }

    // initialize with assetID of the army creating the structure
    public void doInitialize(String ... strings){
        assetID = strings[1];
        //tileID = strings[2]; //PENDING CONTROLLER IMPLEMENTATION
        getPlayer().notify(assetID, this);
    }

    @Override
    public void execute() {
        Map map = getMap();
        Player player = getPlayer();
        // Retrieve colonist unit if any
        String colonistID = player.canCreateStructure(assetID);
        if(colonistID != null){
            System.out.println("Creating Occupance...\n");
            Occupance _o = new AssetOccupance(player.createStructure(assetID));
            System.out.println("new asset occupance with id = " + assetID + "\n");
            // Replace colonist unit occupance with the new structure
            map.getTileState(_o.getTileID()/*maybe can use tileID*/).addOccupance(_o).removeOccupance(colonistID);
            // If the army is now empty, remove its occupance
            if (player.getUnitIDs(assetID).isEmpty()) {
            	  map.getTileState(_o.getTileID()).removeOccupance(assetID);
            }
        }
    }

    @Override
    public double getTurns(){
        return 3.0;
    }
}
