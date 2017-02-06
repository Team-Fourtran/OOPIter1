package application.controllers;

import application.models.commands.CommandGenerator;
import application.views.MainScreen;

import java.util.HashMap;
import java.util.ListIterator;

public class Controller {
    MessageGenerator msgGen;    //The MessageGenerator object, which configures a command and can send it
    MainScreen mainView;        //A reference to the mainView, which will give MessageGenerator the keys pressed
    CommandGenerator cg;        //The Model's CommandGenerator, which will take the MessageGenerator's message

    public Controller(MainScreen mainView, HashMap<String, ListIterator> assetIterators, CommandGenerator cg){
        //Initializes the message generator, setting itself as the reciever, and forwarding the keysPressedList
        this.msgGen = new MessageGenerator(this, mainView.getKeyInformer(), assetIterators);
        this.mainView = mainView;
        this.cg = cg;
    }

    /* Happens when the player changes (aka turn switching). Update the MessageGenerator with the new iterators. */
    public void updateIterators(HashMap<String, ListIterator> assetIterators){
        this.msgGen.updateIterators(assetIterators);
    }

    /* Sends the current status of the MessageGenerator (not generated yet) so it shows in the view */
    protected void updatePanelWithStatus(String s){
        mainView.updateModeCycle(s);
    }

    /* For debug, prints the message to the console. Oh, and actually sends it to the CommandGenerator. */
    protected void handleMsg(String msg){
        System.out.println("From " + this + ": Received message from MsgGen:\t" + msg);

        //Pass the command from the MessageGenerator (Controller-side) to the CommandGenerator(Model-side)
        cg.generateCommand(msg);
    }
}
