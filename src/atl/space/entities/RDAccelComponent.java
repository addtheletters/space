package atl.space.entities;

import java.util.List;

import org.lwjgl.util.vector.Vector3f;


public class RDAccelComponent extends DAccelComponent {
	public float maxAccelForward;
	public float maxAccelBack;
	public float maxAccelSecondary;

	public RDAccelComponent() {
		super();
		maxAccelForward = 0;
		maxAccelBack = 0;
		maxAccelSecondary = 0;
	}

	public RDAccelComponent(float maf, float mab, float mas) {
		super();
		maxAccelForward = maf;
		maxAccelBack = mab;
		maxAccelSecondary = mas;
	}
	public RDAccelComponent(Vector3f acc, float maf, float mab, float mas) {
		super();
		maxAccelForward = maf;
		maxAccelBack = mab;
		maxAccelSecondary = mas;
	}
	
	
	public RDAccelComponent(RDAccelComponent rdac){
		super(rdac);
		maxAccelForward = rdac.maxAccelForward;
		maxAccelBack = rdac.maxAccelBack;
		maxAccelSecondary = rdac.maxAccelSecondary;
	}
	
	public Component clone(){
		return new RDAccelComponent(this);
	}

	public void update(int delta, List<Entity> entities) {
		checkMaxes();
		super.update(delta, entities);
	}

	public Component getStepped(int delta, List<Entity> entities) {
		RDAccelComponent rac = new RDAccelComponent(this);
		rac.update(delta, entities);
		return rac;
	}
	public void checkMaxes(){
		if(accelForward > maxAccelForward){
			accelForward = maxAccelForward;
		}
		if(accelBack > maxAccelBack){
			accelBack = maxAccelBack;
		}
		if(Math.abs(accelSecondary) > maxAccelSecondary){
			accelSecondary = maxAccelSecondary;
		}
	}
	
}
