package application.models.playerAsset;

public class Structure extends PlayerAsset{

    int productionRate;

    public Structure(){
        
        offDamage = 10;
        defDamage = 5;
        armor = 15;
        productionRate = 2;
        health = 10;
        upkeep = 25;
        
    }

    public void healUnit(Unit u){
        //take Unit on same tile and restore health over time
    }

}
