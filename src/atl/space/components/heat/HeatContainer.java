package atl.space.components.heat;

public interface HeatContainer {
	//heat represents some measure of energy. Maybe joules or something.
	//temperature represents an average of energy in the object, like degrees kelvin.
	
	public void setHeat(double heat);
	public void setTemperature(double degrees);
	public void setCapacity(double degrees);
	public void setDegreesPerHeat(double degrees);
	public void addHeat(double heat);
	public void loseHeat(double heat);
	public void addTemperature(double degrees);
	public void addCapacity(double degrees);
	public void transferHeat(double heat, HeatContainer target);
	public double getHeat();
	public double getTemperature();
	public double getCapacity();
	public boolean overCapacity();
	public boolean overPercentCapacity(double percentCapacity);
	public boolean overDistanceCapacity(double degrees);
	//public void ensurePositiveHeat();
}
