package application.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ListIterator;

public class Controller {
    MessageGenerator msgGen;

    public Controller(KeyPressInformer keyInformer, HashMap<String, ListIterator> assetIterators){
        //Initializes the message generator, setting itself as the reciever, and forwarding the keysPressedList
        this.msgGen = new MessageGenerator(this, keyInformer, assetIterators);
    }

    /* Happens when the player changes (aka turn switching) */
    public void updateIterators(HashMap<String, ListIterator> assetIterators){
        this.msgGen.updateIterators(assetIterators);
    }

    /* TODO: For now, just prints messages that it receives */
    protected void handleMsg(String msg){
        System.out.println("From " + this + ":\n\tReceived message from MsgGen:\n\t\t" + msg);
    }
}
