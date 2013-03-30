package atl.space.entities;

import java.util.List;

import org.lwjgl.util.vector.Vector3f;

public class RCTurningComponent extends RTurningComponent {
	private Vector3f forwardStorage;
	private boolean turning180 = false;
	
	public void initiate180(){
		FacingComponent fc = (FacingComponent)owner.getComponent("facing");
		forwardStorage = fc.facing;
		turning180 = true;
	}
	
	public void quit180(){
		turning180 = false;
	}
	public void update(int delta, List<Entity> entities) {
		if(turning180 = false){
			super.update(delta, entities);
		}
		else{
			//TODO: make it do a 180
			//turn at strongestPossibleTurn until facing directly opposite to forwardStorage
			Entity.restrictLength(turn, turnAbility);
			FacingComponent fc = (FacingComponent)owner.getComponent("facing");
			Vector3f.add(fc.facing, turn, fc.facing);
			Entity.restrictLength(fc.facing, 1);	
		}
	}
	public Vector3f getStrongTurn(){
		//TODO: make this work
		return null;
	}
	
	
}
