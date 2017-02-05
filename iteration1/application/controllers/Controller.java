package application.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class Controller {
    MessageGenerator msgGen;

    //Takes an arrayList that at any given time holds String representations of the currently-pressed keys
    public Controller(ArrayList<String> keysPressedList, HashMap<String, Iterator> assetIterators){
        //Initializes the message generator, setting itself as the reciever, and forwarding the keysPressedList
        this.msgGen = new MessageGenerator(this, keysPressedList, assetIterators);
    }

    public void updateIterators(HashMap<String, Iterator> assetIterators){
        this.msgGen.updateIterators(assetIterators);
    }

    /* TODO: For now, just prints messages that it receives */
    protected void handleMsg(String msg){
        System.out.println("From " + this + ":\n\tReceived message from MsgGen:\n\t\t" + msg);
    }
}
