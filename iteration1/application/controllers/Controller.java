package application.controllers;

import application.views.MainScreen;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ListIterator;

public class Controller {
    MessageGenerator msgGen;
    MainScreen mainView;

    public Controller(MainScreen mainView, HashMap<String, ListIterator> assetIterators){
        //Initializes the message generator, setting itself as the reciever, and forwarding the keysPressedList
        this.msgGen = new MessageGenerator(this, mainView.getKeyInformer(), assetIterators);

        this.mainView = mainView;
    }

    /* Happens when the player changes (aka turn switching) */
    public void updateIterators(HashMap<String, ListIterator> assetIterators){
        this.msgGen.updateIterators(assetIterators);
    }

    protected void updatePanelWithStatus(String s){
        mainView.updateModeCycle(s);
    }

    /* TODO: For now, just prints messages that it receives */
    protected void handleMsg(String msg){
        System.out.println("From " + this + ": Received message from MsgGen:\t" + msg);
    }
}
