package atl.space.entities;

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

	public void update(int delta) {
		Entity.restrictLength(accel, maxAccel);
		super.update(delta);
	}

	public Component getStepped(int delta) {
		RAccelComponent rac = new RAccelComponent(this);
		rac.update(delta);
		return rac;
	}

}
