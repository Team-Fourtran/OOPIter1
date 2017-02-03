package aoe;

public class HealingAoE implements AoE {
	private double healingEffect = 20;
	
	@Override
	public double getAreaEffect() {
		return healingEffect;
	}

}
