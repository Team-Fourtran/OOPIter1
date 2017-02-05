package application.models.playerAsset;

public abstract class Unit extends PlayerAsset{

	public Unit() {
		assetID = 'u' + assetID;
	}
	
    protected int movesPerTurn;

}
