package application.models.playerAsset;

//Responsibilities: Weak attacker, can be consumed to make structure
public class Colonist extends Unit{
    
    public Colonist(){
        offDamage = 0;
        defDamage = 0;
        armor = 0;
        movesPerTurn = 2;
        maxHealth = currentHealth = 100;
        upkeep = 1;
    }

    @Override
    public String getType() {
        return "Colonist";
    }
}
