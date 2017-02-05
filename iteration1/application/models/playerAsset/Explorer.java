package application.models.playerAsset;

//Responsibilities: Weak attacker, uncovers resources on map
public class Explorer extends Unit{
    
    public Explorer(){
        super();
        offDamage = 2;
        defDamage = 2;
        armor = 25;
        movesPerTurn = 2;
        maxHealth = currentHealth = 100;
        upkeep = 1;
    }
    
}
