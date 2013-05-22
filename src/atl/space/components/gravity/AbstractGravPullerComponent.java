package atl.space.components.gravity;

import atl.space.components.Component;

public abstract class AbstractGravPullerComponent extends Component implements GravPuller{
	
	//As it is right now,
	//each entity that pulls should only
	//have one of these.
	
	
	public AbstractGravPullerComponent(){
		super("gravpuller"); //sample. Probably not actually used, kinda like AbsMass...
	}
	

	public abstract double getPullForce();

}
