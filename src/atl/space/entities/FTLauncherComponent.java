package atl.space.entities;

import java.util.List;

import org.lwjgl.util.vector.Vector3f;

public class FTLauncherComponent extends FLauncherComponent {
	public Vector3f target;

	// Needs the emission to have a TurnControlComponent
	public FTLauncherComponent(Entity emission, Vector3f dir, float speed) {
		super(emission, dir, speed);
		// id = "emission";
		// System.out.println("set up FTLauncherComponent");
	}
	
	public FTLauncherComponent(FTLauncherComponent ftlc){
		super(ftlc);
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
		Entity temp = new Entity(emission);
		temp.position = new Vector3f(owner.position);
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
