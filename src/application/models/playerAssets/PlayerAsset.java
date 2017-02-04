package application.models.playerAssets;

import application.models.commands.Command;
import application.models.utility.Subscriber;
import java.util.HashMap;
import java.util.List;
import java.util.Queue;

public abstract class PlayerAsset {
    protected String assetID;
    protected String tileID;
    protected Queue<Command> commandQueue;
    private HashMap<Command, List<Subscriber>> subscribers;

    protected void executeNextCommand(List<Subscriber> listeners, Command cmd){

    }

    public void addSubscription(Command cmd, Subscriber s){
        if(subscribers.containsKey(cmd)){
            subscribers.get(cmd).add(s);
        }
    }

    private void notify(Command cmd){
        //subscribers.get(cmd).forEach(subscriber -> subscriber.);
    }
}

