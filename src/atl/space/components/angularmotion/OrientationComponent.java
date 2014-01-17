package atl.space.components.angularmotion;

import org.lwjgl.util.vector.Vector3f;

import atl.space.components.Component;
import atl.space.world.Scene;

public class OrientationComponent extends Component {

	/**
	 * pitch, roll, yaw, done with degree angles.
	 * 
	 * Pitch is around the X axis, 	within the Y-Z plane.
	 * Roll is around the Y axis, 	within the X-Z plane.
	 * Yaw is around the Z axis, 	within the X-Y plane.
	 * 
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
		
		/*
		 * Orientation vector of <0, 0, 0> means facing vector of +1 in the Y axis.
		 */
		Vector3f facing = new Vector3f(0, 1, 0);
		
		facing.setX( (float) (Math.cos(orientation.getY()) * Math.cos(orientation.getZ())) );
		facing.setY( (float) (Math.cos(orientation.getX()) * Math.cos(orientation.getZ())) );
		facing.setZ( (float) (Math.cos(orientation.getY()) * Math.cos(orientation.getX())) );
		
		return facing;
	}
	
	
	@Override
	public Component clone() {
		return new OrientationComponent(this);
	}
	
	private float getReducedAngle(float angle){
		double reduced = (float)(angle % (2 * Math.PI));
		if(reduced < 0){
			return (float)((2*Math.PI) + reduced);
		}
		return (float)reduced;
	}
	
	private boolean isAngleReduced(float angle){
		return angle < 360 && angle > 0;
	}
	
	@Override
	public void update(int delta, Scene sce) {
		// updates fix angles to be 0 <= angle < 360.
		//  perhaps should be 0 <= angle <= 2pi
		// All usage of OrientationComponent should be written to handle
		// values outside this range, but
		// keeping it in is helpful to make debugging easier
		// and avoid overflow errors.
		
		if(!isAngleReduced(orientation.getX())) orientation.setX(getReducedAngle(orientation.getX()));
		if(!isAngleReduced(orientation.getY())) orientation.setY(getReducedAngle(orientation.getY()));
		if(!isAngleReduced(orientation.getZ())) orientation.setZ(getReducedAngle(orientation.getZ()));
	}

}
