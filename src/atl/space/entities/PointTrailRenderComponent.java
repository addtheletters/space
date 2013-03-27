package atl.space.entities;

import static org.lwjgl.opengl.GL11.*;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.util.vector.Vector3f;

public class PointTrailRenderComponent extends RenderableComponent {
	public ArrayList<Vector3f> trail;
	
	public PointTrailRenderComponent(){
		trail = new ArrayList<Vector3f>();
	}
	
	public void update(int delta, List<Entity> entities) {
		trail.add(owner.position);
		//System.out.println(trail.size());
	}

	@Override
	public Component getStepped(int delta, List<Entity> entities) {
		PointTrailRenderComponent prc = new PointTrailRenderComponent();
		prc.update(delta, entities);
		return prc;
	}

	@Override
	public void render() {
		glBegin(GL_POINTS);
			glVertex3f(owner.position.x, owner.position.y, owner.position.z);
			//System.out.println("Pos: " + owner.position.x+ " " + owner.position.y+ " " + owner.position.z);
			//glVertex3f(owner.position.x + 1000, owner.position.y + 1000, owner.position.z+ 1000);
			//glColor3f(1, 1, 1);
			for(int i = 0; i < trail.size(); i++){
				glVertex3f((float)trail.get(i).x, (float)trail.get(i).y, (float)trail.get(i).z);
				//System.out.println(trail.get(i).x+" "+trail.get(i).y+" "+trail.get(i).z);
			}
		glEnd();
		
		//doesn't work for some reason
	}
}
