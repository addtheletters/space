package atl.space.components.emission;

import java.util.List;

import org.lwjgl.util.vector.Vector3f;

import atl.space.components.Component;
import atl.space.components.turn.TurningComponent;
import atl.space.entities.Entity;

public class FLauncherComponent extends LauncherComponent {
	//works with things that have TurningComponents
	public FLauncherComponent(FLauncherComponent flc){
		super(flc);
	}
	public FLauncherComponent(Entity emission, Vector3f expspeed){
		super(emission, expspeed);
	}
	public FLauncherComponent(Entity emission, Vector3f expdir, float speed){
		super(emission);
		expulsionSpeed = new Vector3f(expdir);
		Entity.restrictLength(expulsionSpeed, speed);
	}
	
	public Component clone(){
		return new FLauncherComponent(this);
	}
	
	public void update(int delta, List<Entity> entities) {
		TurningComponent tc = (TurningComponent)owner.getComponent("turning");
		float lengthy = expulsionSpeed.length();
		expulsionSpeed.normalise();
		Vector3f.add(expulsionSpeed, tc.turn, expulsionSpeed);
		Entity.restrictLength(expulsionSpeed, lengthy);
		//Makes the launcher point a new way but at the same speed
	}

}
