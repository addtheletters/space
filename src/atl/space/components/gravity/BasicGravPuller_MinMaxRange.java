package atl.space.components.gravity;

import atl.space.entities.Entity;

public class BasicGravPuller_MinMaxRange extends BasicGravPullerComponent {
	public BasicGravPuller_MinMaxRange(double force, double maxRange,
			double minRange) {
		super(force);
		setMaxRange(maxRange);
		setMinRange(minRange);
	}

	public BasicGravPuller_MinMaxRange(double force) {
		this(force, Double.MAX_VALUE, 0);
	}

	public BasicGravPuller_MinMaxRange() {
		this(1, Double.MAX_VALUE, 0);
	}

	private double maxRange;
	private double minRange;

	// yaaay eclipse
	public double getMinRange() {
		return minRange;
	}

	public void setMinRange(double minRange) {
		this.minRange = minRange;
	}

	public double getMaxRange() {
		return maxRange;
	}

	public void setMaxRange(double maxRange) {
		this.maxRange = maxRange;
	}

	public boolean hasWithinPullableArea(Entity target) {
		float distanceToTarget = owner.getDistance(target);
		return distanceToTarget <= maxRange && distanceToTarget >= minRange;
	}

}
