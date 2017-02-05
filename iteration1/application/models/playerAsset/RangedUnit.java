package application.models.playerAsset;

//Responsibilities: Attacker, can attack over multiple tiles
public class RangedUnit extends Unit{
    
    private final int range; //number of tiles ranger can shoot across?
    
    public RangedUnit(){
        offDamage = 75;
        defDamage = 25;
        armor = 25;
        movesPerTurn = 2;
        maxHealth = currentHealth = 150;
        upkeep = 1;
        range = 2;
    }
            
}
