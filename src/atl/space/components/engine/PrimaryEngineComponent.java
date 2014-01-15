package atl.space.components.engine;

import org.lwjgl.util.vector.Vector3f;

import atl.space.components.Component;
import atl.space.components.angularmotion.old.FacingComponent;


public class PrimaryEngineComponent extends AbstractDirectionalEngineComponent {

	public PrimaryEngineComponent(float thrust, float maxthrust){
		super(thrust, maxthrust, "primaryengine");
	}
	public PrimaryEngineComponent(float maxThrust){
		this(0, maxThrust);
	}
	
	public PrimaryEngineComponent(PrimaryEngineComponent prc) {
		super(prc);
	}

	@Override
	public Vector3f getThrustDirection() {
		FacingComponent fc = (FacingComponent)owner.getComponent("facing");
		return new Vector3f(fc.facing);
	}

	@Override
	public Component clone() {
		return new PrimaryEngineComponent(this);
	}

}
