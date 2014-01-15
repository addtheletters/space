package atl.space.components.angularmotion;

import org.lwjgl.util.vector.Vector3f;

import atl.space.components.Component;
import atl.space.world.Scene;

public class OrientationComponent extends Component {

	/**
	 * pitch, roll, yaw, done with degree angles.
	 */
	public Vector3f orientation;
	
	public OrientationComponent(){
		this(new Vector3f(0, 0, 0));
	}
	public OrientationComponent(Vector3f o){
		super("orientation");
		this.orientation = o;
	}
	public OrientationComponent(OrientationComponent oc){
		super(oc.getId());
		this.orientation = oc.orientation;
	}
	
	/**
	 * Links in to old angular motion system.
	 * @return Unit vector representing the direction the entity is facing.
	 */
	public Vector3f getFacingVector(){
		//TODO this
		Vector3f facing = new Vector3f();
		return facing;
	}
	
	
	@Override
	public Component clone() {
		return new OrientationComponent(this);
	}
	
	@Override
	public void update(int delta, Scene sce) {
		//TODO make it so updates fix angles to be 0 <= angle < 360.
		// All usage of OrientationComponent should be written to handle
		// values outside this range, but
		// keeping it in is helpful to make debugging easier
		// and avoid overflow errors.
	}

}
