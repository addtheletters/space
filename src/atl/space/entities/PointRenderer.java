package atl.space.entities;

import org.lwjgl.util.vector.Vector3f;

public class PointRenderer extends EntityRenderer {
	public Vector3f pos;
	public PointRenderer(Vector3f p){
		pos = p;
	}
	public void render() {
		//openGL stuff, need to integrate somehow with render systems n camera n stuff
		
	}
	public void update(Entity owner){
		pos = owner.position;
	}
}
