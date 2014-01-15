package atl.space.world;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.util.vector.Vector3f;

import atl.space.entities.Entity;

//I have no idea if I actually need this. I don't yet, so meh, not gonna finish it until I do
public class Environment {
	
	public Environment(){
		entities = new ArrayList<Entity>();
	}
	
	public Environment(List<Entity> list){
		entities = list;
	}
	
	private List<Entity> entities; //Turn this into a map maybe of
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
	
	//For the sake of easier replacement of List<Entity> with Environments
	public void add(Entity e){
		addEntity(e);
	}
	
	public void addEntity(Entity e) {
		entities.add(e);
		e.setContainerEnvironment(this);
	}

	public void removeEntity(Entity e) {
		// entities.KEEL
		entities.remove(e); // erm maybe this isn't the most useful... ah well
		e.setContainerEnvironment(null);
	}

	public void updateWorld(int delta) {
		for (Entity e : entities) {
			e.update(delta, this);
		}
	}
	
	public String toString(){
		StringBuffer out = new StringBuffer("Environment: "); 
		for(Entity e: entities) {
			out.append(e.toString());
		}
		return out.toString();
	}
	/*
	 * May end up doing bounds later, but don't need it for anything right now
	 * 
	 * public boolean outsideBounds(Vector3f point){ return
	 * outsideBounds(point.getX(), point.getY(), point.getZ()); } public
	 * abstract boolean outsideBounds(float x, float y, float z);
	 */
}
