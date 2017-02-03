package terrain;


public class Normal implements Terrain {
	private double movementCost = 0;
	
	@Override
	// Returns movement cost for units/armies. Cost is in the interval [0,inf].
	public double getMovementCost() {
		return movementCost;
	}

}
