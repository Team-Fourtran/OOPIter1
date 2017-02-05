package application.models.commands;


import application.models.playerAsset.Player;
import application.models.tileState.Map;

import java.util.ArrayList;

public class CommandGenerator {
    private Player player;
    private Map map;

    public CommandGenerator(Player _p, Map _m){
        this.player = _p;
        this.map = _m;
    }
    public ArrayList<Command> generateCommand(String stringCommand){
        String[] commandArray = stringCommand.split("[_]");
        //Decide which command to create based on first string argument
        //Could be replaced by creational pattern???
        //      --Builder
        //      --Abstract Factory
        //      --Factory Method
        ArrayList<Command> cmd = new ArrayList<>(1);

        switch (commandArray[0]){
            case "MV":  cmd.add(new moveAssetCommand(player, map));
                        break;
            case "NS":  cmd.add(new newStructureCommand(player, map));
                        break;
            default:    cmd.add(new nullCommand(player, map));
        }
        cmd.get(0).initialize(commandArray);

        if(cmd.get(0).needsUnpacked()){
            return new ArrayList<>(((concreteCommand)cmd.get(0)).unpack());
        }
        return cmd;
    }

}
