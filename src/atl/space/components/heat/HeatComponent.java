package atl.space.components.heat;

import java.util.List;

import atl.space.components.Component;
import atl.space.entities.Entity;

public class HeatComponent extends Component implements HeatContainer {
	
	private double heat;
	private double degreesPerHeat;
	private double temperatureCapacity;
	
	public HeatComponent(){
		id = "heat";
		heat = 0;
		degreesPerHeat = 1;
		temperatureCapacity = 100;
	}
	
	public HeatComponent(double heat, double degreesPerHeat, double temperatureCapacity){
		this.heat = heat;
		this.degreesPerHeat = degreesPerHeat;
		this.temperatureCapacity = temperatureCapacity;
	}
	
	public HeatComponent(HeatComponent hc){
		id = hc.getId();
		this.heat = hc.getHeat();
		this.degreesPerHeat = hc.degreesPerHeat;
		this.temperatureCapacity = hc.getCapacity();
	}
	
	@Override
	public void setHeat(double heat) {
		this.heat = heat;
	}

	@Override
	public void setTemperature(double degrees) {
		this.heat = degrees / degreesPerHeat;
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
		this.heat += heat;
	}

	@Override
	public void addTemperature(double degrees) {
		this.heat += degrees / degreesPerHeat;
	}
	
	@Override
	public void addCapacity(double degrees){
		temperatureCapacity += degrees;
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
	public HeatComponent clone() {
		HeatComponent temp = new HeatComponent(this);
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
	}

	@Override
	public Component getStepped(int delta, List<Entity> entities) {
		HeatComponent temp = clone();
		temp.update(delta, entities);
		return temp;
	}
	

}
