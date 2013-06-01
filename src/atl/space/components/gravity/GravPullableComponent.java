package atl.space.components.gravity;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.util.vector.Vector3f;

import atl.space.components.Component;
import atl.space.components.accel.AccelComponent;
import atl.space.components.mass.MassAggregatorComponent;
import atl.space.entities.Entity;

public class GravPullableComponent extends Component implements
		GravPullable {
	
	private static final boolean DEBUG = true;
	
	public double getBaseGravMass() {
		Component massAgg = owner.getComponent("massaggregator");
		return ((MassAggregatorComponent) massAgg).getAggregateMass();
	}

	// Decided that mass be separate from gravity components so mass can be used
	// by stuff like acceleration calculators.
	// So this will get the base mass from a different component.

	public GravPullableComponent() {
		super("gravpullable");
	}

	public GravPullableComponent(GravPullableComponent gpc){
		super(gpc.getId());
	}
	
	public List<String> getPrerequisiteIDs(){
    	ArrayList<String> prids = new ArrayList<String>(1);
		prids.add("massaggregator");
    	return prids;
    }
	
	public double getGravMass() {
		return getBaseGravMass() * getMassModifier();
	}

	protected double getMassModifier(){
		//TODO implement mass modifier thingies, gravitational manipulation >:)
		return 1;
	}
	
	
	public void applyPull(int delta, List<Entity> entities) {
		//TODO delta
		Vector3f netPull = new Vector3f();
		AccelComponent ac = (AccelComponent)owner.getComponent("accel");
		for(Entity e : entities){
			if(e.hasComponent("gravpuller")){
				Vector3f.add(netPull, getPullForce(e), netPull);
				//that SHOULD work...?
				//TODO fix it if it doesn't 
			}
		}
		
		Vector3f.add(ac.accel, netPull, ac.accel);
	}
	
	/*
	 * precondition: e has a GravPuller component
	 */
	protected Vector3f getPullForce(Entity puller){
		Vector3f dir = new Vector3f();
		Vector3f.sub(puller.position, owner.position, dir);
		
		double distance = dir.length();
		
		Component gravpuller = puller.getComponent("gravpuller");
		
		if(((GravPuller)gravpuller).hasWithinPullableArea(owner)){
		/*
		 * http://answers.yahoo.com/question/index?qid=20080117230400AAe7Cyq
		 * heh totally legitimate research meh
		 * 
		 * Assuming getPullForce accounts for the gravitational constant
		 */
			double pullforce = (((GravPuller)gravpuller).getPullForce() * getGravMass()) / Math.pow(distance, 2); //make this formula work
			Entity.restrictLength(dir, (float)pullforce);
		}
		else{
			if(DEBUG) System.out.println("Entity out of pull area, no gravitational effect");
			//if it is not in the pullable area, make the length of the addition 0
			Entity.restrictLength(dir, 0f);
		}
		return dir;
	}
	
	

	@Override
	public Component clone() {
		return new GravPullableComponent(this);
	}

	@Override
	public void update(int delta, List<Entity> entities) {
		applyPull(delta, entities);
	}
}
