package atl.space.components;

import org.lwjgl.util.vector.Vector3f;

public class OrientationComponent extends Component {

	/**
	 * pitch, roll, yaw.
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
	
	@Override
	public Component clone() {
		return new OrientationComponent(this);
	}
	

}
