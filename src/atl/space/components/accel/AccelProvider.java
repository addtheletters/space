package atl.space.components.accel;

import java.util.List;

import org.lwjgl.util.vector.Vector3f;

import atl.space.entities.Entity;

public interface AccelProvider {
	/*
	 * Implemented by anything that provides a thrust unto an object.
	 * Gravity, engines, etc.
	 * Implementations will likely have a prerequisite of movement...
	 */
	public Vector3f getAccel(int delta, List<Entity> entities);
	
	
}
