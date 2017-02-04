package application.models.tileInfo;

public class HealingAoE implements AoE {
	private double healingEffect = 20;
	
	@Override
	public double getAreaEffect() {
		return healingEffect;
	}

}
