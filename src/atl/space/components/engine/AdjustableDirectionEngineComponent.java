package atl.space.components.engine;

import java.util.List;

import org.lwjgl.util.vector.Vector3f;

import atl.space.components.Component;
import atl.space.entities.Entity;

public class AdjustableDirectionEngineComponent extends
		AbstractDirectionalEngineComponent {

	protected Vector3f thrusterAim;
	
	public AdjustableDirectionEngineComponent(float thrust, float maxThrust, Vector3f thrustAim){
		super(thrust, maxThrust);
		thrusterAim = thrustAim;
	}
	
	public AdjustableDirectionEngineComponent(AdjustableDirectionEngineComponent adec) {
		super(adec);
		this.thrusterAim = new Vector3f(adec.getThrustDirection());
	}

	@Override
	public Vector3f getThrustDirection() {
		return new Vector3f(thrusterAim);
	}

	public void setThrusterAim(Vector3f newDir){
		thrusterAim = newDir;
	}
	
	@Override
	public Component clone() {
		return new AdjustableDirectionEngineComponent(this);
	}
	
	@Override
	public void update(int delta, List<Entity> entities) {
		super.update(delta, entities);
		if(thrusterAim.length() != 0){
			thrusterAim.normalise();
		}
	}

}
