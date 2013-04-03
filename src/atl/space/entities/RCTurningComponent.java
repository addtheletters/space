package atl.space.entities;

import java.util.List;

import org.lwjgl.util.vector.Vector3f;

public class RCTurningComponent extends RTurningComponent {
	private boolean turningToTarget = false;
	private Vector3f target;
	
	public void initiate180(){
		FacingComponent fc = (FacingComponent)owner.getComponent("facing");
		fc.facing.negate(target); //not sure if this will work or throw null pointers
		turningToTarget = true;
	}
	
	public void quitTurn(){
		turningToTarget = false;
	}
	
	public void update(int delta, List<Entity> entities) {
		if(!turningToTarget){
			super.update(delta, entities);
		}
		else{
			//TODO: make it turn
			//turn at strongestPossibleTurn until facing directly opposite to forwardStorage
			Entity.restrictLength(turn, turnAbility);
			FacingComponent fc = (FacingComponent)owner.getComponent("facing");
			Vector3f.add(fc.facing, turn, fc.facing);
			Entity.restrictLength(fc.facing, 1);	
		}
	}
	public void setTarget(Vector3f trgt){
		target = trgt;
	}
	
	public Vector3f getStrongTurn(){
		
		//TODO: make this work
		return null;
	}
	
	
}
