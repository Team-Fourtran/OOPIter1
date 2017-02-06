package application.models.commands;

import application.models.playerAsset.Player;
import application.models.tileState.AssetOccupance;
import application.models.tileState.Map;
import application.models.tileState.Occupance;

public class NewStructureCommand extends ConcreteCommand{
    private String assetID;
    //private String tileID; //PENDING CONTROLLER IMPLEMENTATION

    NewStructureCommand(Player _p, Map _m){
        super(_p, _m);
    }

    public void doInitialize(String ... strings){
        assetID = strings[1];
        //tileID = strings[2]; //PENDING CONTROLLER IMPLEMENTATION
        getPlayer().notify(assetID, this);
    }

    @Override
    public void execute() {
        Map map = getMap();
        Player player = getPlayer();
        String colonistID = player.canCreateStructure(assetID);
        if(colonistID != null){
            System.out.println("Creating Occupance...\n");
            Occupance _o = new AssetOccupance(player.createStructure(assetID));
            System.out.println("new asset occupance with id = " + assetID + "\n");
            map.getTileState(_o.getTileID()/*maybe can use tileID*/).addOccupance(_o).removeOccupance(colonistID);
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
