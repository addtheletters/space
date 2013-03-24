package atl.space.entities;

import java.util.List;

import org.lwjgl.util.vector.Vector3f;

public class RAccelComponent extends AccelComponent {
	public float maxAccel;

	public RAccelComponent() {
		super();
		maxAccel = 0;
	}

	public RAccelComponent(Vector3f av, float ma) {
		super(av);
		maxAccel = ma;
		Entity.restrictLength(accel, maxAccel);
	}
	
	public RAccelComponent(RAccelComponent rac){
		super(rac);
		maxAccel = rac.maxAccel;
		Entity.restrictLength(accel, maxAccel);
	}

	public void update(int delta, List<Entity> entities) {
		Entity.restrictLength(accel, maxAccel);
		super.update(delta, entities);
	}

	public Component getStepped(int delta, List<Entity> entities) {
		RAccelComponent rac = new RAccelComponent(this);
		rac.update(delta, entities);
		return rac;
	}

}
