package atl.space.components.linearmotion.accel;

import org.lwjgl.util.vector.Vector3f;

import atl.space.world.Scene;

public interface AccelProvider {
	/*
	 * Implemented by anything that provides a thrust unto an object.
	 * Gravity, engines, etc.
	 * Implementations will likely have a prerequisite of movement...
	 */
	public Vector3f getAccel(int delta, Scene sce); //entity list temporary for
																//reference to container environment
	
	
}
