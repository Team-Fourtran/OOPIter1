package application.models.tileInfo;
/*
 * See AoE. This provides a positive amount of health to apply to assets
 */
public class HealingAoE implements AoE {
	private double healingEffect = 20;
	
	@Override
	// Returns double representing a double to add to a player assets' existing health
	public double getAreaEffect() {
		return healingEffect;
	}

}
