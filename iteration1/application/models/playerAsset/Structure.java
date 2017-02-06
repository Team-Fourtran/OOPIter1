package application.models.playerAsset;

import application.models.commands.Command;
import java.util.*;

public class Structure extends PlayerAsset{

    int productionRate;

    public Structure(){

        offDamage = 75;
        defDamage = 75;
        armor = 150;
        productionRate = 2;
        maxHealth = currentHealth = 200;
        upkeep = 1;
        
    }

    //heal unit on same tile as structure
    public void healUnit(Unit u){
        u.heal(50);
    }

}
