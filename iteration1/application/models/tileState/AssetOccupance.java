package application.models.tileState;

import application.models.playerAsset.PlayerAsset;

public class AssetOccupance extends Occupance{
    private PlayerAsset asset;

    public AssetOccupance(PlayerAsset _asset){
        this.asset = _asset;
    }

//    @Override
//    void updateLocationIDofAsset() {
//        asset.setLocation(tileID);
//    }

    @Override
    public String getAssetID() {
        return asset.getID();
    }

    @Override
    public String getTileID() {
        return asset.getLocation();
    }

}
