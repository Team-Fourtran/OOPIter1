package application.models.tileProperties;

public class DamageAoE implements AoE {
	private double damageEffect = -20;

	@Override
	public double getAreaEffect() {
		return damageEffect;
	}

}
