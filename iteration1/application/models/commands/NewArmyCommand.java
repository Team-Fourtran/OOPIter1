package application.models.commands;

import application.models.playerAsset.Player;
import application.models.tileState.Map;

public class NewArmyCommand extends ConcreteCommand{
    private String destinationTileID;
    private String[] unitIDList;

    NewArmyCommand(Player _p, Map _m) {
        super(_p, _m);
    }

    @Override
    public void doInitialize(String... strings){
        destinationTileID = strings[1];
        if (strings.length < 2){
            System.out.println("Not enough arguments to create army!");
            return;
        }
        //Populates an array of unit IDs comprising the army
        unitIDList = new String[strings.length - 2];
        for (int i = 2; i < strings.length; i++){
            unitIDList[i-2] = strings[i];
        }
        this.unpack();
    }

    @Override
    public void setPacking(){
        needsUnpacked = true;
    }

    @Override
    public void unpack(){ }
}
