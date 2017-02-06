package application.models.playerAsset;

public abstract class Unit extends PlayerAsset{
	
    protected int movesPerTurn;

    public void heal(int healAmount){
    	if (currentHealth + healAmount > maxHealth)
    		currentHealth = maxHealth;
    	else
    		currentHealth += healAmount;
	}

	public abstract String getUnitType();
}
