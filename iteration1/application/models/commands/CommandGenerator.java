package application.models.commands;


import application.models.playerAsset.Player;
import application.models.tileState.Map;
/*
 * This class is to allow for the generation of Commands
 */
public class CommandGenerator {
    private Player player;
    private Map map;

    // Specify the player that this command belongs to and a reference to the map
    public CommandGenerator(Player _p, Map _m){
        this.player = _p;
        this.map = _m;
    }
    
    //Decide which command to create based on first string argument
    public void generateCommand(String stringCommand){
        String[] commandArray = stringCommand.split("[_]");
        Command cmd;

        switch (commandArray[0]){
        	case "IU":	cmd = new InitialUnitsCommand(player, map);
        				break;
            case "MV":  cmd = new MoveAssetCommand(player, map);
            			break;
            case "MD": 	cmd = new MoveDirectionCommand(player, map);
            			break;
            case "NS":  cmd = new NewStructureCommand(player, map);
            			break;
            case "NU":  cmd = new NewUnitCommand(player, map);
                        break;
            case "NA":  cmd = new NewArmyCommand(player, map);
                        break;
            case "H":   cmd = new HealCommand(player, map);
                        break;
            case "MRP": cmd = new MoveRallyPointCommand(player, map);
                        break;
            default:    cmd = new NullCommand(player, map);
        }
        // Initialize the command, set the type and arguments specified
        cmd.initialize(commandArray);
    }

}
