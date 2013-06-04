package atl.space.components.emission;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.util.vector.Vector3f;

import atl.space.components.Component;
import atl.space.components.MovementComponent;
import atl.space.components.turn.TurnControlComponent;
import atl.space.entities.Entity;

public class FTLauncherComponent extends FacingLauncherComponent {
	public Vector3f target;
	//Sets the emission to track the target.
	// Needs the emission to have a TurnControlComponent
	public FTLauncherComponent(Entity emission, Vector3f dir, float speed) {
		super(emission, dir, speed);
		// id = "emission";
		// System.out.println("set up FTLauncherComponent");
	}
	
	public FTLauncherComponent(FTLauncherComponent ftlc){
		super(ftlc);
	}
	
	public List<String> getPrerequisiteIDs(){
    	ArrayList<String> prids = new ArrayList<String>(2);
		prids.add("turning");
		prids.add("movement");
    	return prids;
    }
	
	public Component clone(){
		return new FTLauncherComponent(this);
	}
	
	public void setTarget(Vector3f target) {
		this.target = target;
	}

	public void trigger(List<Entity> entities) {
		Vector3f netVel = new Vector3f();
		Vector3f.add(
				((MovementComponent) owner.getComponent("movement")).speed,
				expulsionSpeed, netVel);
		Entity temp = buildEmission();
		temp.position = new Vector3f(owner.getPosition());
		
		//now that we have buildEmission we can just modify that rather than adding
		//all this crap down here in trigger
		//TODO actually fix this
		
		if (!temp.hasComponent("movement")) {
			temp.addComponent(new MovementComponent(netVel));
		} else {
			MovementComponent mc = (MovementComponent) temp
					.getComponent("movement");
			mc.speed = netVel;
		}
		if (temp.hasComponent("turncontrol")) {
			// System.out.println("Setting target");
			TurnControlComponent tcc = (TurnControlComponent) temp
					.getComponent("turncontrol");
			tcc.setTarget(target);
			tcc.initiateTurn();
		} else {
			System.err.println("No turn control in emission");
		}
		entities.add(temp);
	}
}
