package atl.space.components.angularmotion.old;

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

public class TurningComponent extends RenderableComponent { //don't use this, use restricted version
	public Vector3f turn;
	
	public static final float renderLength = 50;
	public TurningComponent(){
		this(new Vector3f());
	}
	public TurningComponent(Vector3f t){
		super("turning");
		turn = new Vector3f(t);
	}
	
	public TurningComponent(TurningComponent tc){
		super(tc.getId());
		turn = new Vector3f(tc.turn);
	}
	
	public List<String> getPrerequisiteIDs(){
    	ArrayList<String> prids = new ArrayList<String>(1);
		prids.add("facing");
    	return prids;
    }
	
	public Component clone(){
		return new TurningComponent(this);
	}
	
	public void update(int delta, List<Entity> entities) {
		FacingComponent fc = (FacingComponent)owner.getComponent("facing");
		Vector3f.add(fc.facing, turn, fc.facing);
		Entity.restrictLength(fc.facing, 1);
	}

	public Component getStepped(int delta, List<Entity> entities) {
		TurningComponent tc = new TurningComponent(turn);
		tc.update(delta, entities);
		return tc;
	}
	@Override
	public void render() {
		FacingComponent fc = (FacingComponent)owner.getComponent("facing");
		//System.out.println(fc.facing + " " + turn);
		float[] coords = new float[]{owner.position.x + fc.facing.x*FacingComponent.renderLength, owner.position.y + fc.facing.y*FacingComponent.renderLength, owner.position.z + fc.facing.z*FacingComponent.renderLength};
		Vector3f normTurn = new Vector3f(turn);
		if(normTurn.length() != 0){
			normTurn.normalise();
		}
		glBegin(GL_LINES);
		glColor4f(0, 0, 1, 1);
		glVertex3f(coords[0], coords[1], coords[2]);
		glColor4f(0, 1, 0, 1);
		glVertex3f(coords[0]+normTurn.x*renderLength, coords[1]+normTurn.y*renderLength, coords[2]+normTurn.z*renderLength);
		glColor4f(1, 1, 1, 1);
		glEnd();
	}


}
