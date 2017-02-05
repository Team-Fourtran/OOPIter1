package application.models.playerAsset;

//Responsibilities: Weak attacker, can be consumed to make structure
public class Colonist extends Unit{
    
    public Colonist(){
    	super();
        offDamage = 0;
        defDamage = 0;
        armor = 0;
        movesPerTurn = 2;
        health = 3;
        upkeep = 1;
    }
}
