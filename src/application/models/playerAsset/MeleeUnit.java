package application.models.playerAsset;

//Responsibilities: Weak attacker, uncovers resources on map
public class MeleeUnit extends Unit{
    
    public MeleeUnit(){
        
        offDamage = 5;
        defDamage = 5;
        armor = 5;
        movesPerTurn = 2;
        health = 5;
        upkeep = 1;
    }
}
