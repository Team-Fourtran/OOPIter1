package application.models.tileState;

/*
 * Serves as the association between TileStates and PlayerAssets
 */

public abstract class Occupance {
    public abstract String getAssetID(); // retrieve ID of asset
    public abstract String getTileID(); // retrieve ID of tile
    public abstract void updateAssetLocation(String tileID); // update the location of this occupance
    public void healPlayerAssets(){ } // triggers healing of units in this occupance
}

