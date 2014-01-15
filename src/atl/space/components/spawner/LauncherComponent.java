package atl.space.components.spawner;


import org.lwjgl.util.vector.Vector3f;

import atl.space.components.linearmotion.MovementComponent;
import atl.space.entities.Entity;

public abstract class LauncherComponent extends MSpawnerComponent {
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
	
	/*public Component clone(){
		return new LauncherComponent(this);
	}*/
	
	
	@Override
	protected void applyEffect(Entity temp) {
		Vector3f netVel = new Vector3f();
		Vector3f.add(((MovementComponent)owner.getComponent("movement")).velocity, expulsionSpeed, netVel);
		if(!temp.hasComponent("movement")){
			if(DEBUG) System.out.println("DEBUG: No movement component detected, adding...");
			temp.addComponent(new MovementComponent(netVel));
		}
		else{
			MovementComponent mc = (MovementComponent)temp.getComponent("movement");
			mc.velocity = netVel;
		}
	}
	
	/*public Component getStepped(int delta, List<Entity> entities) {
		LauncherComponent lc = new LauncherComponent(this);
		lc.update(delta, entities);
		return lc;
	}*/
	
}
