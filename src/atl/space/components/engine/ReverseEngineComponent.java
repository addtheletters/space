package atl.space.components.engine;

import org.lwjgl.util.vector.Vector3f;

import atl.space.components.Component;
import atl.space.components.turn.FacingComponent;

public class ReverseEngineComponent extends AbstractDirectionalEngineComponent {

	public ReverseEngineComponent(ReverseEngineComponent rec) {
		super(rec);
	}

	@Override
	public Vector3f getThrustDirection() {
		FacingComponent fc = (FacingComponent)owner.getComponent("facing");
		Vector3f temp = new Vector3f(fc.facing);
		temp.negate();
		return temp;
	}

	@Override
	public Component clone() {
		return new ReverseEngineComponent(this);
	}

}
