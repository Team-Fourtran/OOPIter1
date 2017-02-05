package application.controllers;

import java.util.ArrayList;
import java.util.HashMap;

public class KeyPressInformer {
    private HashMap<String, Boolean> keyMap;
    private ArrayList<KeyPressListener> clients;

    public KeyPressInformer(HashMap<String, Boolean> keyList){
        this.keyMap = keyList;
        this.clients = new ArrayList<>();
    }

    protected void registerClient(KeyPressListener client){
        clients.add(client);
    }

    public void update(String keyname, Boolean status){
        keyMap.put(keyname,status);
        for(int i = 0; i < clients.size(); i++){
            clients.get(i).updateKeysPressed(keyMap);
        }
    }
}
