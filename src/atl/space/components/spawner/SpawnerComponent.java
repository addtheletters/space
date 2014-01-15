package atl.space.components.spawner;

import java.util.List;

import org.lwjgl.util.vector.Vector3f;

import atl.space.components.Component;
import atl.space.components.Triggerable;
import atl.space.entities.Entity;

public abstract class SpawnerComponent extends Component implements Triggerable {
	public SpawnerComponent(String id){
		super(id);
	}
	
	public SpawnerComponent(SpawnerComponent ec){
		super(ec.getId());
	}
	
	@Override
	public void trigger(List<Entity> entities) {
		if (canEmit()) { 
			Entity temp = buildEmission();
			temp.position = new Vector3f(owner.position);
			applyEffect(temp);
			entities.add(temp);
		}
	}
	
	protected abstract Entity buildEmission();
	protected abstract boolean canEmit();
	protected abstract void applyEffect(Entity temp);

}