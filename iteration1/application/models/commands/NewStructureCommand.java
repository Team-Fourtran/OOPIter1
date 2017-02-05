package application.models.commands;

import application.models.playerAsset.Player;
import application.models.playerAsset.PlayerAsset;
import application.models.tileState.AssetOccupance;
import application.models.tileState.Map;
import application.models.tileState.Occupance;

public class NewStructureCommand extends ConcreteCommand{
    private String assetID;

    NewStructureCommand(Player _p, Map _m){
        super(_p, _m);
    }

    public void doInitialize(String ... strings){
        assetID = strings[1];
    }

    @Override
    public void execute() {
        Map map = getMap();
        Player player = getPlayer();
        if(player.canCreateStructure(assetID)){
            System.out.println("Creating Occupance...\n");
            PlayerAsset s = player.createStructure(assetID);
            Occupance _o = new AssetOccupance(s);
            System.out.println("new asset occupance with id = " + assetID + "\n");
            map.getTileState(s.getLocation()).addOccupance(_o).removeOccupance(assetID);
        }
    }
}
