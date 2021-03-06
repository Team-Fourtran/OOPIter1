package application.models.tileInfo;
/*
 * See Item interface. This item provides a negative health effect.
 */
public class ObstacleItem implements Item {
	private double itemEffect = -30;
	
	@Override
	// Returns a double representing how many health points to add to a PlayerAsset's health
	public double getItemEffect() {
		return itemEffect;
	}
	
}
