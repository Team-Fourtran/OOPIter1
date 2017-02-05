package application.models.tileState;

import application.models.playerAsset.PlayerAsset;

/**Serves as the association between TileStates and PlayerAssets
 *
 */

public abstract class Occupance {
    abstract void updateLocationIDofAsset();
    public abstract String getAssetID();
    public abstract String getTileID();
    public abstract void setTileID(String id);
}

