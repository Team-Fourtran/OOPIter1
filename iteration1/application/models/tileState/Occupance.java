package application.models.tileState;

/**Serves as the association between TileStates and PlayerAssets
 *
 */

public abstract class Occupance {
    //abstract void updateLocationIDofAsset();
    public abstract String getAssetID();
    public abstract String getTileID();
    public abstract void updateAssetLocation(String tileID);
}

