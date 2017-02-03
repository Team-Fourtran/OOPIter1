package terrain;


public class Slowing implements Terrain {
	private double movementCost = 50;
	
	@Override
	// Returns movement cost for units/armies. Cost is in the interval [0,inf].
	public double getMovementCost() {
		return movementCost;
	}

}
