package atl.space.entities;

import static org.lwjgl.opengl.GL11.GL_POINTS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glVertex3f;

import java.util.List;

public class InfoRenderComponent extends RenderableComponent {
	//Will display an info box with statistics next to it's entity
	
	public InfoRenderComponent(){
		id = "inforender";
	}
	
	@Override
	public void render() {
		// TODO Hmm. How do I do this...
		glBegin(GL_POINTS);
		glVertex3f(owner.position.x, owner.position.y, owner.position.z);
		glEnd();
	}

	@Override
	public void update(int delta, List<Entity> entities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Component getStepped(int delta, List<Entity> entities) {
		// TODO Auto-generated method stub
		return null;
	}

}
