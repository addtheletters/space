package atl.space.entities;

import static org.lwjgl.opengl.GL11.GL_LINES;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glColor4f;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glVertex3f;

import java.util.List;

import org.lwjgl.util.vector.Vector3f;

public class FacingComponent extends RenderableComponent {
	public static final float renderLength = 100;
	public Vector3f facing;
	public FacingComponent(){
		id = "facing";
		facing = new Vector3f(0, 0, 0);
	}
	public FacingComponent(Vector3f f){
		id = "facing";
		facing = new Vector3f(f);
		facing.normalise();
	}
	public FacingComponent(FacingComponent fc){
		id = fc.id;
		facing = new Vector3f(fc.facing);
		facing.normalise();
		//Entity.restrictLength(facing, 1); //does the same thing as facing.normalise()
	}
	/*public void restrictLength(){
		if(facing.length() > 1 || facing.length() < -1){ //set length of the vector to 1
			facing.scale((float)(1/facing.length()));
		}
	}*/
	
	public void update(int delta, List<Entity> entities) {
		facing.normalise();
	}

	@Override
	public Component getStepped(int delta, List<Entity> entities) {
		FacingComponent fc = new FacingComponent(this);
		fc.update(delta, entities);
		return fc;
	}
	@Override
	public void render() {
		glBegin(GL_LINES);
		glColor4f(0, 1, 1, 1);
		glVertex3f(owner.position.x, owner.position.y, owner.position.z);
		glColor4f(1, 0, 0, 1);
		glVertex3f(owner.position.x + facing.x*renderLength, owner.position.y + facing.y*renderLength, owner.position.z + facing.z*renderLength);
		glColor4f(1, 1, 1, 1);
		glEnd();
	}
	
	
}
