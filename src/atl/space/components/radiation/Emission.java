package atl.space.components.radiation;

public class Emission {
	private int wavelength;
	private EmissionPattern pattern;
	public int getWavelength() {
		return wavelength;
	}
	public void setWavelength(int wavelength) {
		this.wavelength = wavelength;
	}
	public EmissionPattern getPattern() {
		return pattern;
	}
	public void setPattern(EmissionPattern pattern) {
		this.pattern = pattern;
	}
	
}
