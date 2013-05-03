package atl.space.components.emission;

import java.util.List;

import org.lwjgl.util.vector.Vector3f;

import atl.space.components.Component;
import atl.space.components.MovementComponent;
import atl.space.entities.Entity;

public class LauncherComponent extends MEmissionComponent {
	public Vector3f expulsionSpeed;
	
	public LauncherComponent(){
		this(new Vector3f());
	}
	
	public LauncherComponent(LauncherComponent lc){
		super(lc);
		expulsionSpeed = new Vector3f(lc.expulsionSpeed);
	}
	public LauncherComponent(Vector3f expspeed){
		super("launcher");
		expulsionSpeed = expspeed;
	}
	
	public Component clone(){
		return new LauncherComponent(this);
	}
	
	@Override
	public void trigger(List<Entity> entities) {
		Vector3f netVel = new Vector3f();
		Vector3f.add(((MovementComponent)owner.getComponent("movement")).speed, expulsionSpeed, netVel);
		Entity temp = buildEmission();
		if(!temp.hasComponent("movement")){
			temp.addComponent(new MovementComponent(netVel));
		}
		else{
			MovementComponent mc = (MovementComponent)temp.getComponent("movement");
			mc.speed = netVel;
		}
		entities.add(temp);
	}
	
	public Component getStepped(int delta, List<Entity> entities) {
		LauncherComponent lc = new LauncherComponent(this);
		lc.update(delta, entities);
		return lc;
	}
	
}
