package application.models.commands;


public class CommandGenerator {

    public CommandGenerator(){

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
            case "MV":  cmd = new moveUnitCommand(commandArray[1], commandArray[2]);
            case "NS":  cmd = new newStructureCommand(commandArray[1]);
            default:    cmd = new nullCommand();
        }
        return cmd;
    }

}
