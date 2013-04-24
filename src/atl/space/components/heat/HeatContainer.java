package atl.space.components.heat;

public interface HeatContainer {
	public void setHeat(double heat);
	public void setTemperature(double degrees);
	public void setCapacity(double degrees);
	public void setDegreesPerHeat(double degrees);
	public void addHeat(double heat);
	public void addTemperature(double degrees);
	public void addCapacity(double degrees);
	public double getHeat();
	public double getTemperature();
	public double getCapacity();
	public boolean overCapacity();
	public boolean overPercentCapacity(double percentCapacity);
	public boolean overDistanceCapacity(double degrees);
}
