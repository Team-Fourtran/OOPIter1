package application.models.playerAsset;

public abstract class PlayerAsset {

    protected int offDamage;
    protected int defDamage;
    protected int armor;
    protected int health;
    protected int upkeep;
    protected boolean poweredUp;
    protected String locationID;
    protected String assetID;

    /*
    public PlayerAsset() {
    	assetID = Integer.toString(availableID);
    	availableID++;
    }
    */

    public void setID(String id){
        assetID = id;
    }

    public String getID(){
        return assetID;
    }

    public String getLocation(){
        return locationID;
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
