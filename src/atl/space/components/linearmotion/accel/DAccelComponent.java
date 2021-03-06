package atl.space.components.linearmotion.accel;

import java.util.List;

import org.lwjgl.util.vector.Vector3f;

import atl.space.components.Component;
import atl.space.components.angularmotion.old.FacingComponent;
import atl.space.entities.Entity;

/*
 * deprecated. Replacement in progress at atl.space.components.engine
 */
public class DAccelComponent extends BasicAccelComponent {
	//Used to model something like spacecraft engines. D represents directional.
	//OK, so now that gravity is implemented, this won't work right in combination. Need new engine components that
	//add to acceleration to replace these directional acceleration components.
	public float accelForward;
	public float accelBack;
	public float accelSecondary;
	public Vector3f secondaryAccelVector;
	
	public DAccelComponent() {
		super();
		accelForward = 0;
		accelBack = 0;
		accelSecondary = 0;
		secondaryAccelVector = new Vector3f();
	}
	public DAccelComponent(Vector3f initAccel) {
		super(initAccel);
		accelForward = 0;
		accelBack = 0;
		accelSecondary = 0;
		secondaryAccelVector = new Vector3f();
	}
	public DAccelComponent(float af, float ab, float as, Vector3f sav) {
		accelForward = af;
		accelBack = ab;
		accelSecondary = as;
		secondaryAccelVector = sav;
		calcNetAccel();
	}
	
	public DAccelComponent(DAccelComponent dac){
		super(dac);
		accelForward = dac.accelForward;
		accelBack = dac.accelBack;
		accelSecondary = dac.accelSecondary;
		secondaryAccelVector = dac.secondaryAccelVector;
		calcNetAccel();
	}

	public Component clone(){
		return new DAccelComponent(this);
	}
	
	public void update(int delta, List<Entity> entities) {
		//Modify acceleration vector
		calcNetAccel();
		//super.update(delta, entities);
	}

	public Component getStepped(int delta, List<Entity> entities) {
		DAccelComponent dac = new DAccelComponent(this);
		dac.update(delta, entities);
		return dac;
	}
	
	public void setSecondaryAccelVector(Vector3f sav){
		secondaryAccelVector = sav;
	}
	
	public void calcNetAccel(){
		checkAccel();
		if(secondaryAccelVector.length() != 0){
		Entity.restrictLength(secondaryAccelVector, accelSecondary);
		}
		FacingComponent fc = (FacingComponent)owner.getComponent("facing");
		Vector3f tempAccelV = new Vector3f(fc.facing);
		tempAccelV.scale(accelForward - accelBack);
		Vector3f.add(tempAccelV, secondaryAccelVector, tempAccelV);
		accel = tempAccelV;
	}
	
	public void checkAccel(){
		if(accelForward < 0){
			accelForward = 0;
		}
		if(accelBack < 0){
			accelBack = 0;
		}
	}

}
