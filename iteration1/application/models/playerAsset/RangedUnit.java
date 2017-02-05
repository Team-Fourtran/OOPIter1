package application.models.playerAsset;

//Responsibilities: Attacker, can attack over multiple tiles
public class RangedUnit extends Unit{
    
    private final int range;
    
    public RangedUnit(){
    	super();
        offDamage = 5;
        defDamage = 3;
        armor = 1;
        movesPerTurn = 2;
        health = 5;
        upkeep = 1;
        range = 2;
    }
            
}
