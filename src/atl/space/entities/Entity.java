package atl.space.entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.lwjgl.util.vector.Vector3f;

import atl.space.components.Component;
import atl.space.components.PrerequisiteNotFoundException;
import atl.space.components.render.RenderableComponent;

public class Entity {
	
	private static boolean DEBUG = true;
	
	public String id;
	public Vector3f position;

	/**
	 * List of all components
	 */
	ArrayList<Component> components = null;
	HashMap<String, Component> componentHash = null;
	
	
	public static void restrictLength(Vector3f v, float length){
		if(v.length() != 0){
			//v.normalise();
			v.scale(length / v.length());
		}
	}
	
	public static boolean isSame(Vector3f a, Vector3f b){
		if(a.getX() == b.getX() && a.getY() == b.getY() && a.getZ() == b.getZ()){
			//TODO make sure this works
			//may need to alter this so there's rounding, so vectors that are like .001 off count as the same
			return true;
		}
		return false;
	}
	
	public static ArrayList<Component> cloneComponentList(List<Component> toClone){
		ArrayList<Component> temp = new ArrayList<Component>();
		for(Component c : toClone){
			temp.add(c.clone());
		}
		return temp;
		
	}
	
	//TODO: Check constructors to make sure they make new entities with new components with new vectors
	
	public Entity(String id) {
		this(id, new Vector3f(0, 0, 0));
	}
	
	public Entity(String id, Vector3f pos){
		this.id = id;
		position = pos;
		componentHash = new HashMap<String, Component>();
		components = new ArrayList<Component>();
		if(DEBUG) System.out.println("Making Entity of ID: " + id +" at " + pos);
	}
	
	public Entity(Entity e) {
		this(e.id, new Vector3f(e.position));
		this.addComponents(cloneComponentList(e.getComponents()));
		//TODO components?!?! Make sure this works
	}

	public Entity(String id, Vector3f position, ArrayList<Component> cs) {
		this(id, position);
		addComponents(cs);
	}
	
	public void addComponent(Component component){
		try {
			addComponent_Unhandled(component);
		} catch (PrerequisiteNotFoundException e) {
			System.out.println("[WARN] "+e.getMessage());
		}
	}
	
	public void addComponent_Unhandled(Component component) throws PrerequisiteNotFoundException{
		List<String> neededComponentIDs = checkPrerequisites(component);
		if(neededComponentIDs == null){
			
			if (componentHash.containsKey(component.getId().toLowerCase())) {
				throw new IllegalArgumentException("[WARN] Component with name: " + component.getId() + " has already been added to entity " + this.id);
			}
			component.setOwnerEntity(this);
			components.add(component);
			componentHash.put(component.getId().toLowerCase(), component);
		
		}
		else{
			throw new PrerequisiteNotFoundException(neededComponentIDs);
		}
	}
	
	public void addComponents(List<Component> cs) {
		for (Component c : cs) {
				addComponent(c);
		}
	}
	
	public List<String> checkPrerequisites(Component toAdd){
		//TODO mess with this 
		//return of null means prerequisites are satisfied.
		//return of a list means prerequisite(s) are missing.
		List<String> prIDs = toAdd.getPrerequisiteIDs();
		if(prIDs == null) return null;
		Set<String> existingIDs = componentHash.keySet();
		
		if(DEBUG) System.out.println("DEBUG: Checking for: " + prIDs);
		if(DEBUG) System.out.println("DEBUG: Already existing: " + existingIDs);
		
		prIDs.removeAll(existingIDs);
		
		if(DEBUG) {
			if(prIDs.size() > 0) System.out.println("DEBUG: Still Missing: " + prIDs);
			else System.out.println("DEBUG: No prerequisites missing for " + toAdd);
		}
		
		if(prIDs.size() == 0) return null;
		return prIDs;
	}
	
	
	public Component getComponent(String id) {
		return componentHash.get(id.toLowerCase());
	}

	public List<Component> getComponents(String id){
		List<Component> comps = new ArrayList<Component>();
		for (Component comp : components) {
			if (comp.getId().equalsIgnoreCase(id))
				comps.add(comp);
		}
		return comps;
	}
	
	public List<Component> getComponents(){
		return components;
	}
	
	public boolean hasComponent(String id) {
		return componentHash.containsKey(id.toLowerCase());
	}

	public Vector3f getPosition() {
		return position;
	}
	
	public float getDistance(Entity target){
		return getDistance(target.getPosition());
	}
	
	public float getDistance(Vector3f target){
		Vector3f temp = new Vector3f();
		Vector3f.sub(position, target, temp);
		return temp.length();
	}

	public String getId() {
		return id;
	}
	
	public Entity getNearest(List<Entity> entities){
		Entity nearest = entities.get(0);
		float longestdistance = getDistance(nearest.getPosition());
		//Vector3f temp = new Vector3f();
		for(Entity e : entities){
			float dist = getDistance(e.getPosition());
			if(dist < longestdistance && this != e){
				nearest = e;
				longestdistance = dist;
			}
		}
		return nearest;
	}
	
	public Entity getNearestTarget(List<Entity> entities){
		Entity nearest = entities.get(0);
		float longestdistance = getDistance(nearest.getPosition());
		//Vector3f temp = new Vector3f();
		for(Entity e : entities){
			float dist = getDistance(e.getPosition());
			if(dist < longestdistance && this != e && e.id != "missile"){
				nearest = e;
				longestdistance = dist;
			}
		}
		return nearest;
	}

	public void setPosition(Vector3f position) {
		this.position = position;
	}

	public void update(int delta, List<Entity> entities) {
		for (Component component : components) {
			component.update(delta, entities);
		}
	}

	public void render() {
		//System.out.println("Render!");
		for (Component component : components) {
			//System.out.println("Thing!");
			if (component.isRenderable()) {
				RenderableComponent rc = (RenderableComponent) component;
				rc.render();
				//System.out.println("render!");
			}
		}
	}

	public Entity getStepped(int delta, List<Entity> entities) {
		Entity stepped = new Entity(id, position, components);
		stepped.update(delta, entities);
		return stepped;
	}

}
