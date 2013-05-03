package atl.space.components.render;

import static org.lwjgl.opengl.GL11.GL_POINTS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glVertex3f;

import java.util.List;

import atl.space.components.Component;
import atl.space.entities.Entity;

public class InfoRenderComponent extends RenderableComponent {
	//Will display an info box with statistics next to it's entity
	
	public InfoRenderComponent() {
		super("inforender");
	}
	public InfoRenderComponent(InfoRenderComponent irc) {
		this();
	}
	public Component clone() {
		return new InfoRenderComponent(this);
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
}
