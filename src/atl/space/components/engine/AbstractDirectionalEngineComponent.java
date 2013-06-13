package atl.space.components.engine;

import java.util.List;

import org.lwjgl.util.vector.Vector3f;

import atl.space.entities.Entity;

public abstract class AbstractDirectionalEngineComponent extends
		AbstractEngineComponent {

	private static final boolean DEBUG = true;

	protected float thrust;
	protected float maxThrust;

	public AbstractDirectionalEngineComponent(float thrust, float maxThrust, String id) {
		super(id);
		this.thrust = thrust;
		this.maxThrust = maxThrust;
	}

	public AbstractDirectionalEngineComponent() {
		this(0, Float.MAX_VALUE, "abstractengine");
	}

	public AbstractDirectionalEngineComponent(
			AbstractDirectionalEngineComponent pec) {
		super();
		this.thrust = pec.thrust;
		this.maxThrust = pec.maxThrust;
	}

	public List<String> getPrerequisiteIDs() {
		List<String> prids = super.getPrerequisiteIDs();
		prids.add("facing");
		return prids;
	}

	public abstract Vector3f getThrustDirection();

	@Override
	public void update(int delta, List<Entity> entities) {
		if (thrust < 0) {
			if (DEBUG)
				System.out.println("Engine thrust (" + thrust
						+ ") negative, setting to zero");
			thrust = 0;
		}
		if (thrust > maxThrust) {
			if (DEBUG)
				System.out.println("Engine thrust (" + thrust
						+ ") above set limit, restricting to " + maxThrust);
			thrust = maxThrust;
		}
	}

	@Override
	public Vector3f getThrust() {
		Vector3f tempThrustV = getThrustDirection();
		tempThrustV.scale(thrust);
		return tempThrustV;
	}
	
	@Override
	public void setThrustForce(float thrust) {
		this.thrust = thrust;
	}
	public void setMaxThrustForce(float maxThrust){
		this.maxThrust = maxThrust;
	}

}
