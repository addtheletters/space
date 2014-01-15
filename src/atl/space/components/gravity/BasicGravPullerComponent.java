package atl.space.components.gravity;

import atl.space.components.render.RenderableComponent;
import atl.space.entities.Entity;

public class BasicGravPullerComponent extends RenderableComponent implements GravPuller{
	
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
	public boolean hasWithinPullableArea(Entity target) {
		return true; //basic ones just pull errything
	}

	@Override
	public void render() {
		//TODO something?
	}

}
