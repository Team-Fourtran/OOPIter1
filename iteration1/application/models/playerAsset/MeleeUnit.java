package application.models.playerAsset;

//Responsibilities: Weak attacker, uncovers resources on map
public class MeleeUnit extends Unit{
    
    public MeleeUnit(){
    	super();
        offDamage = 5;
        defDamage = 5;
        armor = 50;
        movesPerTurn = 2;
        maxHealth = currentHealth = 150;
        upkeep = 1;
    }
}
