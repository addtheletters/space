package atl.space.entities;

import java.util.List;

import org.lwjgl.util.vector.Vector3f;

public class MovementComponent extends Component {
	public Vector3f speed;
	public MovementComponent(){
		id = "movement";
		speed = new Vector3f(0, 0, 0);
	}
	public MovementComponent(Vector3f s){
		id = "movement";
		speed = s;
	}
	public void update(int delta, List<Entity> entities) {
		Vector3f.add(owner.position, speed, owner.position);
	}

	public Component getStepped(int delta, List<Entity> entities) {
		MovementComponent cs = new MovementComponent(speed);
		cs.update(delta, entities);
		return cs;
	}

}
