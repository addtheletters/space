package atl.space.components.engine;

import org.lwjgl.util.vector.Vector3f;

import atl.space.components.Component;
import atl.space.components.turn.FacingComponent;


public class PrimaryEngineComponent extends AbstractDirectionalEngineComponent {

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
