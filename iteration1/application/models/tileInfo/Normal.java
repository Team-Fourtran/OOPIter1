package application.models.tileInfo;


public class Normal implements Terrain {
	private double movementCost = 1;
	
	@Override
	// Returns movement cost for units/armies. Cost is in the interval [0,inf].
	public double getMovementCost() {
		return movementCost;
	}

}
