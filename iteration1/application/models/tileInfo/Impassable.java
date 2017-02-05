package application.models.tileInfo;


public class Impassable implements Terrain {
	private double movementCost = Double.POSITIVE_INFINITY;
	
	@Override
	// Returns movement cost for units/armies. Cost is in the interval [0,inf].
	public double getMovementCost() {
		return movementCost;
	}

}
