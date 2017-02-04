package application.models.tileInfo;

public class InstantDeathAoE implements AoE {
	double deathEffect = Double.NEGATIVE_INFINITY;

	@Override
	public double getAreaEffect() {
		return deathEffect;
	}
	
	
}
