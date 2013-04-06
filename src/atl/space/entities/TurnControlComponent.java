package atl.space.entities;

import java.util.List;

import org.lwjgl.util.vector.Vector3f;

public class TurnControlComponent extends Component implements Triggerable {
	public boolean turningToTarget = false;
	public boolean hardTurn = false;

	// Hard turn: Turn at max possible turn speed until facing target
	// Soft turn: Set turn vector to just point at the target, resulting
	// in a more gradual turn
	public Vector3f target;

	public TurnControlComponent() {
		id = "turncontrol";
		this.target = new Vector3f();
	}

	public TurnControlComponent(Vector3f target) {
		id = "turncontrol";
		this.target = target;
	}

	public TurnControlComponent(TurnControlComponent tcc) {
		id = tcc.id;
		this.target = new Vector3f(tcc.target);
		turningToTarget = tcc.turningToTarget;
		hardTurn = tcc.hardTurn;
	}

	public void initiate180() {
		FacingComponent fc = (FacingComponent) owner.getComponent("facing");
		fc.facing.negate(target); // not sure if this will work or throw null
									// pointers
		turningToTarget = true;
	}

	public void initiateTurn() {
		turningToTarget = true;
	}

	public void quitTurn() {
		turningToTarget = false;
	}

	public void update(int delta, List<Entity> entities) {
		if (turningToTarget) {
			FacingComponent fc = (FacingComponent) owner.getComponent("facing");
			Vector3f normTarget = new Vector3f(target);
			normTarget.normalise();
			if (!Entity.isSame(fc.facing, normTarget)) { //Make sure it works.
				TurningComponent tc = (TurningComponent) owner
						.getComponent("turning");
				if (!hardTurn) {
					tc.turn = getSoftTurn();
				} else {
					tc.turn = getHardTurn(fc.facing);
				}
			}
			else{ //it has successfully turned to face the target
				//System.out.println("Turn complete!");
				quitTurn();
			}
		}
	}

	public void setTarget(Vector3f trgt) {
		target = trgt;
		//System.out.println("target set to " + trgt);
	}

	public Vector3f getHardTurn(Vector3f facing) {
		// TODO: make this work
		//Cross the dir and a vector from position to target
		//Cross new vector with dir
		Vector3f temp = new Vector3f();
		Vector3f.sub(target, facing, temp); //get a vector representing target in relation to pos
		Vector3f.cross(facing, temp, temp);
		Vector3f.cross(facing, temp, temp);
		return temp;
	}

	public Vector3f getSoftTurn() {
		Vector3f temp = new Vector3f();
		Vector3f.sub(target, owner.position ,temp);
		return temp;
	}
	
	/*public Vector3f getTurn(float angle){
		 * Gets a turn at angle to the facing vector.
		 * Example: hardTurn is always 90 deg to facing
		 * softTurn's angle can vary depending on the target and the facing vector 
	}
	*/
	
	@Override
	public Component getStepped(int delta, List<Entity> entities) {
		//I'm not exactly sure why I have these. Not currently using them for anything and any
		//possible uses could prolly be done faster with some simple math.
		//I don't really want to delete all of them though in case I need them later...
		return null;
	}

	@Override
	public void trigger(List<Entity> entities) {
		initiateTurn();
	}

}
