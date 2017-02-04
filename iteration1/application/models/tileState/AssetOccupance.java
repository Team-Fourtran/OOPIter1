package application.models.tileState;

import application.models.playerAsset.PlayerAsset;

public class AssetOccupance extends Occupance{
    private PlayerAsset asset;

    public AssetOccupance(PlayerAsset _asset){
        this.asset = _asset;
    }

    @Override
    void updateLocationIDofAsset() {
        //asset.setLocationID();
    }

    @Override
    public String getAssetID() {
        //asset.getAssetID();
        return "";
    }

    @Override
    public String getTileID() {
        //asset.getTileID();
        return "";
    }

}
