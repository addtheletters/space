package atl.space.components.gravity;

import java.util.List;

import atl.space.components.Component;
import atl.space.entities.Entity;

public class BasicGravPullerComponent extends Component implements GravPuller{
	
	//As it is right now,
	//each entity that pulls should only
	//have one of these.
	private double basicPullForce;
	
	public BasicGravPullerComponent(){
		this(1); //sample. Probably not actually used, kinda like AbsMass...
	}
	
	public BasicGravPullerComponent(double basicForce){
		super("gravpuller");
		basicPullForce = basicForce;
	}
	public BasicGravPullerComponent(BasicGravPullerComponent bgpc){
		super(bgpc.getId());
		basicPullForce = bgpc.getPullForce();
	}
	
	public double getPullForce(){
		return basicPullForce;
	}

	@Override
	public BasicGravPullerComponent clone() {
		BasicGravPullerComponent temp = new BasicGravPullerComponent(this);
		return temp;
	}

	@Override
	public void update(int delta, List<Entity> entities) {
		//do nothing
	}

	@Override
	public boolean hasWithinPullableArea(Entity target) {
		return true; //basic ones just pull errything
	}

}
