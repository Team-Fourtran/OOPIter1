package application.models.playerAsset;

import application.models.commands.Command;
import java.util.*;

public abstract class Unit extends PlayerAsset{
	
    protected int movesPerTurn;

    public void heal(int healAmount){
    	if (currentHealth + healAmount > maxHealth)
    		currentHealth = maxHealth;
    	else
    		currentHealth += healAmount;
	}

	public String getType(){
    	return "this is a unit";
	}
}
