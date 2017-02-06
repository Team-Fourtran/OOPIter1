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
        	case "IU":	cmd.add(new InitialUnitsCommand(player, map));
        				break;
            case "MV":  cmd.add(new MoveAssetCommand(player, map));
            			break;
            case "MD": 	cmd.add(new MoveDirectionCommand(player, map));
            			break;
            case "NS":  cmd.add(new NewStructureCommand(player, map));
            			break;
            case "NU":  cmd.add(new newUnitCommand(player, map));
                        break;
            default:    cmd.add(new NullCommand(player, map));
        }
        cmd.get(0).initialize(commandArray);

        if(cmd.get(0).needsUnpacked()){
            return new ArrayList<>(((ConcreteCommand)cmd.get(0)).unpack());
        }
        return cmd;
    }

}
