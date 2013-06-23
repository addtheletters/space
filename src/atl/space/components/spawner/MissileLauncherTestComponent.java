package atl.space.components.spawner;

import java.util.List;

import org.lwjgl.util.vector.Vector3f;

import atl.space.components.Component;
import atl.space.entities.Entity;
import atl.space.entities.EntityBuilder;

public class MissileLauncherTestComponent extends FTLauncherComponent {
	
	private int tickTime;
	
	public MissileLauncherTestComponent(Vector3f dir,
			float speed) {
		super(dir, speed);
	}
	public MissileLauncherTestComponent(MissileLauncherTestComponent mltc){
		super(mltc);
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
		tickTime --;
	}
	
}
