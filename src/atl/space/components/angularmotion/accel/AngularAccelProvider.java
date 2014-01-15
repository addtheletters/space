package atl.space.components.angularmotion.accel;

import org.lwjgl.util.vector.Vector3f;

import atl.space.world.Scene;

public interface AngularAccelProvider {
	/*
	 * Implemented by anything capable of giving an object angular acceleration / torque.
	 * 
	 * Processed by NetAngularAccelComponent.
	 */
	
	public Vector3f getAngularAccel(int delta, Scene sce);
	
}
