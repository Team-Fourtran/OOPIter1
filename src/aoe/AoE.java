package aoe;

/*
 * This class is for AoE which can provide the results of their effect (in how many health points to add/remove)
 */
public interface AoE {
	// Returns double representing a double to add to a player asset's existing health
	public double getAreaEffect();
}
