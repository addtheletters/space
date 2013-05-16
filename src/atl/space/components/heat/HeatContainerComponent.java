package atl.space.components.heat;

import java.util.List;

import atl.space.components.Component;
import atl.space.entities.Entity;

public class HeatContainerComponent extends Component implements HeatContainer {
	
	private double heat;
	private double degreesPerHeat;
	private double temperatureCapacity;
	
	public HeatContainerComponent(){
		this(0, 1, 100);
	}
	
	public HeatContainerComponent(double heat, double degreesPerHeat, double temperatureCapacity){
		super("heatcontainer");
		this.heat = heat;
		this.degreesPerHeat = degreesPerHeat;
		this.temperatureCapacity = temperatureCapacity;
	}
	
	public HeatContainerComponent(HeatContainerComponent hc){
		super(hc.getId());
		this.heat = hc.getHeat();
		this.degreesPerHeat = hc.degreesPerHeat;
		this.temperatureCapacity = hc.getCapacity();
	}
	
	@Override
	public void setHeat(double heat) {
		this.heat = heat;
		ensurePositiveHeat();
	}

	@Override
	public void setTemperature(double degrees) {
		setHeat( degrees / degreesPerHeat);
	}
	
	@Override
	public void setCapacity(double degrees) {
		temperatureCapacity = degrees;
	}
	
	@Override
	public void setDegreesPerHeat(double degrees){
		degreesPerHeat = degrees;
	}
	
	@Override
	public void addHeat(double heat) {
		setHeat(this.heat + heat);
	}

	@Override
	public void addTemperature(double degrees) {
		setHeat(this.heat +( degrees / degreesPerHeat));
	}
	
	@Override
	public void addCapacity(double degrees){
		setCapacity(temperatureCapacity + degrees);
	}
	
	public double getCapacity(){
		return temperatureCapacity;
	}
	
	@Override
	public double getHeat() {
		return this.heat;
	}

	@Override
	public double getTemperature() {
		return heat * degreesPerHeat;
	}

	@Override
	public boolean overCapacity() {
		return getTemperature() > temperatureCapacity;
	}

	@Override
	public boolean overPercentCapacity(double percentCapacity) {
		return getTemperature() > (temperatureCapacity * percentCapacity);
	}

	@Override
	public boolean overDistanceCapacity(double degrees) {
		return getTemperature() > (temperatureCapacity - degrees);
	}

	@Override
	public HeatContainerComponent clone() {
		HeatContainerComponent temp = new HeatContainerComponent(this);
		return temp;
	}

	@Override
	public void update(int delta, List<Entity> entities) {
		//for all components
		//if they generate heat
		//add their heat
		//if they disperse heat
		//disperse their heat
		//if they suck heat
		//calculate all the things
		
		//We prolly need some kinda HeatSystemElement class/interface or something to make these parts work right together.
	}
	
	@Override
	public void transferHeat(double heat, HeatContainer target) {
		loseHeat(heat);
		target.addHeat(heat);
	}
	
	public void ensurePositiveHeat(){
		if(heat < 0){
			heat = 0;
		}
	}

	@Override
	public void loseHeat(double heat) {
		addHeat(-1 * heat);
	}
}
