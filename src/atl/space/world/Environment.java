package atl.space.world;

import java.util.List;

import org.lwjgl.util.vector.Vector3f;

import atl.space.entities.Entity;

//I have no idea if I actually need this. I don't yet, so meh, not gonna finish it until I do
public class Environment {
	
	private List<Entity> entities; // TODO: Turn this into a map maybe of
									// position to entity so getEntity() is
									// easier?

	// or maybe add a map too.
	public List<Entity> getEntities() {
		return entities;
	}

	public Entity getEntity(Vector3f position) {
		// TODO: Make this work.
		return null;
	}

	public void addEntity(Entity e) {
		entities.add(e);
	}

	public void removeEntity(Entity e) {
		// entities.KEEL
		entities.remove(e); // erm maybe this isn't the most useful... ah well
	}

	public void updateWorld(int delta) {
		for (Entity e : entities) {
			e.update(delta, entities); // TODO: Change update(int, list<Entity>)
										// to update(int, Environment)
		}
	}
	/*
	 * public boolean outsideBounds(Vector3f point){ return
	 * outsideBounds(point.getX(), point.getY(), point.getZ()); } public
	 * abstract boolean outsideBounds(float x, float y, float z);
	 */
}
