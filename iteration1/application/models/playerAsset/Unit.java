package application.models.playerAsset;

import application.models.commands.Command;
import java.util.*;

public abstract class Unit extends PlayerAsset{
	
    protected int movesPerTurn;

    public void heal(){
    	if (currentHealth + 50 > maxHealth)
    		currentHealth = maxHealth;
    	else
    		currentHealth += 50;
	}

	public String getType(){
    	return "this is a unit";
	}
}
