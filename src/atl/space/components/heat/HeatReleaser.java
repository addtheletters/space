package atl.space.components.heat;

public interface HeatReleaser {
	//Has an internal heat, is releasing it into spaaaace
	//Hmmm. Do I add heat dispersal, or just have hotspots?
	
	/*
	 * No-arg version releases what it's supposed to based on temperature.
	 */
	public void releaseHeat();
	
	/*
	 * Multiplies release by the factor. Could be for when in-atmo or other odd circumstances.
	 */
	public void releaseHeat(double factor);
	//public void releaseTemperature(double degrees);
	
}
