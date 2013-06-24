package atl.space.components.spawner;

import java.util.List;

import org.lwjgl.util.vector.Vector3f;

import atl.space.components.Component;
import atl.space.entities.Entity;
import atl.space.entities.EntityBuilder;

public class MissileLauncherTestComponent extends FTLauncherComponent {
	
	private int tickTime;
	private int launchDelay;
	private static final int DEFAULT_DELAY = 100;
	
	public MissileLauncherTestComponent(Vector3f dir,
			float speed, int tickDelay) {
		super(dir, speed);
		launchDelay = tickDelay;
	}
	public MissileLauncherTestComponent(Vector3f dir, float speed){
		this(dir, speed, DEFAULT_DELAY);
	}
	public MissileLauncherTestComponent(MissileLauncherTestComponent mltc){
		super(mltc);
		launchDelay = DEFAULT_DELAY;
	}
	
	@Override
	public void trigger(List<Entity> entities) {
		super.trigger(entities);
		tickTime = launchDelay;
	}
	
	@Override
	protected Entity buildEmission() {
		return buildMissile(.01f, 0f, .01f);
	}
	
	private Entity buildMissile(float maxAccelF, float maxAccelB, float maxturn){
		return EntityBuilder.missile(maxAccelF, maxAccelB, maxturn);
	}

	@Override
	protected boolean canEmit() {
		return tickTime == 0;
	}

	@Override
	public Component clone() {
		return new MissileLauncherTestComponent(this);
	}
	
	@Override
	public void update(int delta, List<Entity> entities) {
		super.update(delta, entities);
		if(tickTime > 0) tickTime --;
	}
	
}
