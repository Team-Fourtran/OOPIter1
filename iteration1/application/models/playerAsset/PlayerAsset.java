package application.models.playerAsset;

public abstract class PlayerAsset {

    protected int offDamage;
    protected int defDamage;
    protected int armor;
    protected int maxHealth;
    protected int currentHealth;
    protected int upkeep;
    protected boolean poweredUp;
    protected String locationID;
    protected String assetID;


    public void setID(String id){
        assetID = id;
    }

    public String getID(){
        return assetID;
    }

    public String getLocation(){
        return locationID;
    }
    public int getOffDamage(){
        return offDamage;
    }
    public int getDefDamage(){
        return defDamage;
    }
    public int getArmor(){
        return armor;
    }
    public int getMaxHealth(){
        return maxHealth;
    }
    public int getCurrentHealth(){
        return currentHealth;
    }
    public boolean getPoweredUp(){
        return poweredUp;
    }
    public void setLocation(String location){
        locationID = location;
    }

    public int getUpkeep(){
        return upkeep;
    }

    public void powerUp(){
        if (!poweredUp)         //incorrect logic, will be fixed
            upkeep *= 4;
    }

    public void powerDown(){
        if (poweredUp)
            upkeep = (int)Math.ceil(.25*upkeep);
    }

}
