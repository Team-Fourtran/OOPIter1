package tileProperties;

/*
 * Defines interface for Terrain. Terrain affects the speed at which armies/units can travel
 */
public interface Terrain {
	// Returns movement cost for units/armies.
	public double getMovementCost();
}
