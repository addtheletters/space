package atl.space.entities;

//import org.lwjgl.util.vector.Vector3f;

public class PointRenderComponent extends RenderableComponent {
	
	public void update(int delta) {
		//do nothing
	}

	@Override
	public Component getStepped(int delta) {
		PointRenderComponent prc = new PointRenderComponent();
		prc.update(delta);
		return prc;
	}

	@Override
	public void render() {
		//openGL stuffs, render system?
		//float[] point = new float[]{owner.position.x, owner.position.y, owner.position.z};
		
		//request a point somewhere, when all renders are called compiles requests into a VBO
		
	}
}
