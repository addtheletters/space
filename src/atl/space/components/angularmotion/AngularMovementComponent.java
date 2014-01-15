package atl.space.components.angularmotion;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.util.vector.Vector3f;

import atl.space.components.Component;
import atl.space.components.OrientationComponent;
import atl.space.world.Scene;

public class AngularMovementComponent extends Component {

	public Vector3f angularVelocity;
	
	public AngularMovementComponent(){
		this(new Vector3f(0, 0, 0));
	}
	public AngularMovementComponent(Vector3f angVelocity){
		super("angularmovement");
		this.angularVelocity = angVelocity;
	}
	public AngularMovementComponent(AngularMovementComponent amc) {
		super(amc.getId());
		this.angularVelocity = amc.angularVelocity;
	}
	
	public List<String> getPrerequisiteIDs(){
    	ArrayList<String> prids = new ArrayList<String>(1);
		prids.add("orientation");
    	return prids;
    }
	
	
	@Override
	public Component clone() {
		return new AngularMovementComponent(this);
	}

	@Override
	public void update(int delta, Scene sce) {
		OrientationComponent oc = (OrientationComponent)owner.getComponent("orientation");
		Vector3f.add(oc.orientation, angularVelocity, oc.orientation);
	}

}
