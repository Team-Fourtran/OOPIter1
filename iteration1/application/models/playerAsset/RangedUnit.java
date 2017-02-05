package application.models.playerAsset;

//Responsibilities: Attacker, can attack over multiple tiles
public class RangedUnit extends Unit{
    
    private final int range;
    
    public RangedUnit(){
    	super();
        offDamage = 5;
        defDamage = 3;
        armor = 25;
        movesPerTurn = 2;
        maxHealth = currentHealth = 150;
        upkeep = 1;
        range = 2;
    }
            
}
