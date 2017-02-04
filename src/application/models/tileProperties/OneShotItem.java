package application.models.tileProperties;

public class OneShotItem implements Item {
	private double itemEffect = -50;
	
	@Override
	public double getItemEffect() {
		return itemEffect;
	}

}
