package application.models.commands;


import application.models.playerAsset.Player;
import application.models.tileState.Map;

public class CommandGenerator {
    private Player player;
    private Map map;

    public CommandGenerator(Player _p, Map _m){
        this.player = _p;
        this.map = _m;
    }
    public Command generateCommand(String stringCommand){
        String[] commandArray = stringCommand.split("[_]");
        Command cmd;
        //Decide which command to create based on first string argument
        //Could be replaced by creational pattern???
        //      --Builder
        //      --Abstract Factory
        //      --Factory Method

        switch (commandArray[0]){
            case "MV":  cmd = new moveAssetCommand(player, map);
            case "NS":  cmd = new newStructureCommand(player, map);
            default:    cmd = new nullCommand(player, map);
        }
        cmd.initialize(commandArray);

        return cmd;
    }

}
