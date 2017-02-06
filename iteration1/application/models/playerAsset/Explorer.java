package application.models.playerAsset;

//Responsibilities: Weak attacker, uncovers resources on map
public class Explorer extends Unit{
    
    public Explorer(){
        offDamage = 20;
        defDamage = 10;
        armor = 25;
        movesPerTurn = 2;
        maxHealth = currentHealth = 100;
        upkeep = 1;
    }

    @Override
    public String getType() {
        return "Explorer";
    }
}
