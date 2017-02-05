package application.models.playerAsset;

//Responsibilities: Weak attacker, uncovers resources on map
public class Explorer extends Unit{
    
    public Explorer(){
        super();
        offDamage = 2;
        defDamage = 2;
        armor = 0;
        movesPerTurn = 2;
        health = 5;
        upkeep = 1;
    }
    
}
