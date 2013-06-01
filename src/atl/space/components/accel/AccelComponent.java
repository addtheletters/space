package atl.space.components.accel;

import static org.lwjgl.opengl.GL11.GL_LINES;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glColor4f;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glVertex3f;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.util.vector.Vector3f;

import atl.space.components.Component;
import atl.space.components.MovementComponent;
import atl.space.components.render.RenderableComponent;
import atl.space.entities.Entity;

public class AccelComponent extends RenderableComponent { //don't use this, use restricted version
	public Vector3f accel;
	private static float renderLength = 1000;
	public AccelComponent(){
		this(new Vector3f(0, 0, 0));
	}
	public AccelComponent(Vector3f a){
		super("accel");
		accel = a;
	}
	public AccelComponent(AccelComponent ac){
		super(ac.getId());
		accel = new Vector3f(ac.accel);
	}
	
	public List<String> getPrerequisiteIDs(){
    	ArrayList<String> prids = new ArrayList<String>(1);
		prids.add("movement");
    	return prids;
    }
	
	public Component clone(){
		return new AccelComponent(this);
	}
	
	public void update(int delta, List<Entity> entities) {
		MovementComponent mc = (MovementComponent)owner.getComponent("movement");
		Vector3f.add(mc.speed, accel, mc.speed);
	}
	public Component getStepped(int delta, List<Entity> entities) {
		AccelComponent ac = new AccelComponent(this);
		ac.update(delta, entities);
		return ac;
	}
	@Override
	public void render() {
		glBegin(GL_LINES);
		glColor4f(1, 0, 1, 1);
		glVertex3f(owner.position.x, owner.position.y, owner.position.z);
		glColor4f(0, 1, 0, 1);
		glVertex3f(owner.position.x + accel.x*renderLength, owner.position.y + accel.y*renderLength, owner.position.z + accel.z*renderLength);
		glColor4f(1, 1, 1, 1);
		glEnd();
	}

}
