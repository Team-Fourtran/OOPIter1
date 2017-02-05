package application.controllers;

import java.util.ArrayList;

public class Controller {
    MessageGenerator msgGen;

    //Takes an arrayList that at any given time holds String representations of the currently-pressed keys
    public Controller(ArrayList<String> keysPressedList){
        //Initializes the message generator, setting itself as the reciever, and forwarding the keysPressedList
        this.msgGen = new MessageGenerator(this, keysPressedList);
    }

    /* TODO: For now, just prints messages that it receives */
    protected void handleMsg(String msg){
        System.out.println("From " + this + ":\n\tReceived message:\n\t" + msg);
    }
}
