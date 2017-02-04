package application.models.playerAsset;

public class Structure implements PlayerAsset{

    private String structureID;
    private String tileID;
    
    int offDamage;
    int defDamage;
    int armor;
    int productionRate;
    int health;
    int upkeep;
    
    public Structure(){
        
        offDamage = 10;
        defDamage = 5;
        armor = 15;
        productionRate = 2;
        health = 10;
        upkeep = 25;
        
    }
    
    public void healUnit(Unit u){
        
    }

    public String getTileID(){
        return structureID;
    }

}
