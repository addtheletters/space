package atl.space.entities;

import java.util.List;

import org.lwjgl.util.vector.Vector3f;

public class AccelComponent extends Component { //don't use this, use restricted version
	public Vector3f accel;
	public AccelComponent(){
		id = "accel";
		accel = new Vector3f(0, 0, 0);
	}
	public AccelComponent(Vector3f a){
		id = "accel";
		accel = a;
	}
	public AccelComponent(AccelComponent ac){
		id = ac.getId();
		accel = ac.accel;
	}
	
	public void update(int delta, List<Entity> entities) {
		MovementComponent mc = (MovementComponent)owner.getComponent("movement");
		Vector3f.add(mc.speed, accel, mc.speed);
	}
	public Component getStepped(int delta, List<Entity> entities) {
		AccelComponent ac = new AccelComponent(this);
		ac.update(delta, entities);
		return ac;
	}

}
