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
		// TODO
		// Update owner's TurningComponent
		if (turningToTarget) {
			FacingComponent fc = (FacingComponent) owner.getComponent("facing");
			
			if (true) { // not facing the target
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
	}

	public Vector3f getHardTurn(Vector3f facing) {
		// TODO: make this work
		return null;
	}

	public Vector3f getSoftTurn() {
		Vector3f temp = new Vector3f();
		Vector3f.sub(target, owner.position ,temp);
		// TODO: make sure this works
		return temp;
	}

	@Override
	public Component getStepped(int delta, List<Entity> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void trigger(List<Entity> entities) {
		initiateTurn();
	}

}
