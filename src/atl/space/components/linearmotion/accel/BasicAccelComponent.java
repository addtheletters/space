package atl.space.components.linearmotion.accel;

import static org.lwjgl.opengl.GL11.GL_LINES;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glColor4f;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glVertex3f;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.util.vector.Vector3f;

import atl.space.components.Component;
import atl.space.components.render.RenderableComponent;
import atl.space.entities.Entity;
import atl.space.world.Scene;

public class BasicAccelComponent extends RenderableComponent implements AccelProvider{

	private static final boolean RENDER = true;
	
	public Vector3f accel;
	private static float renderLength = 1000;
	public BasicAccelComponent(){
		this(new Vector3f(0, 0, 0));
	}
	public BasicAccelComponent(Vector3f a){
		super("basicaccel");
		accel = a;
	}
	public BasicAccelComponent(BasicAccelComponent ac){
		super(ac.getId());
		accel = new Vector3f(ac.accel);
	}
	
	public List<String> getPrerequisiteIDs(){
    	ArrayList<String> prids = new ArrayList<String>(1);
		prids.add("accel");
    	return prids;
    }
	
	public Component clone(){
		return new BasicAccelComponent(this);
	}
	
	public void update(int delta, List<Entity> entities) {
		//do nothing?
	}
	public Component getStepped(int delta, List<Entity> entities) {
		BasicAccelComponent ac = new BasicAccelComponent(this);
		ac.update(delta, entities);
		return ac;
	}
	@Override
	public void render() {
		if(RENDER){
		glBegin(GL_LINES);
		glColor4f(1, 0, 1, 1);
		glVertex3f(owner.position.x, owner.position.y, owner.position.z);
		glColor4f(0, 1, 0, 1);
		glVertex3f(owner.position.x + accel.x*renderLength, owner.position.y + accel.y*renderLength, owner.position.z + accel.z*renderLength);
		glColor4f(1, 1, 1, 1);
		glEnd();
		}
	}
	@Override
	public Vector3f getAccel(int delta, Scene sce) {
		return accel;
	}

}
