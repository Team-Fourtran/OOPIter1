package application.models.tileInfo;


public class Slowing implements Terrain {
	private double movementCost = 2;
	
	@Override
	// Returns movement cost for units/armies. Cost is in the interval [0,inf].
	public double getMovementCost() {
		return movementCost;
	}

}
