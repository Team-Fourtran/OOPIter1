package application.models.commands;
/*
 * Interface for Commands, which encapsulate arguments, what to do upon execution, and how many turns the operation takes
 */
public interface Command {
    void execute(); // executes Command functionality
    void initialize(String... strings); //  retrieves type of command and its arguments
    double getTurns(); // returns number of turns this command takes to execute
}
