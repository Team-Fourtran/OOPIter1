package application.models.commands;

public interface Command {
    void execute();
    void initialize(String... strings);
    boolean needsUnpacked();
}
