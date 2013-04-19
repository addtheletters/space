package atl.space.components;

import java.util.List;

import atl.space.entities.Entity;

public interface Triggerable {
	public abstract void trigger(List<Entity> entities);
}
