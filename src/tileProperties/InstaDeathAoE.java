package tileProperties;

public class InstaDeathAoE implements AoE {
	double deathEffect = Double.NEGATIVE_INFINITY;

	@Override
	public double getAreaEffect() {
		return deathEffect;
	}
	
	
}
