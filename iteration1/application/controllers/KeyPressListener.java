package application.controllers;

import java.util.HashMap;

public interface KeyPressListener {
    void updateKeysPressed(HashMap<String, Boolean> kp);
}
