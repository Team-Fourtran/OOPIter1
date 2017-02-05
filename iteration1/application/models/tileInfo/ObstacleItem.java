package application.models.tileInfo;

public class ObstacleItem implements Item {
	private double itemEffect = -30;
	
	@Override
	public double getItemEffect() {
		return itemEffect;
	}
	
}
