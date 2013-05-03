package atl.space.components.render;

import static org.lwjgl.opengl.GL11.GL_POINTS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glVertex3f;

import java.util.List;
//import org.lwjgl.util.vector.Vector3f;

import atl.space.components.Component;
import atl.space.entities.Entity;

public class PointRenderComponent extends RenderableComponent {
	public PointRenderComponent(){
		super("pointrendercomponent");
	}
	public PointRenderComponent(PointRenderComponent prc){
		this();
	}
	
	public Component clone(){
		return new PointRenderComponent(this);
	}
	
	public void update(int delta, List<Entity> entities) {
		//do nothing
	}

	@Override
	public void render() {
		//openGL stuffs, render system?
		//float[] point = new float[]{owner.position.x, owner.position.y, owner.position.z};
		
		//request a point somewhere, when all renders are called compiles requests into a VBO
		//System.out.println("render!");
		glBegin(GL_POINTS);
			glVertex3f(owner.position.x, owner.position.y, owner.position.z);
		glEnd();
	}
}
