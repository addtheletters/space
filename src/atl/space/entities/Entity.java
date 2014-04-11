package atl.space.entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.lwjgl.util.vector.Vector3f;

import atl.space.components.Component;
import atl.space.components.PrerequisiteNotFoundException;
import atl.space.components.data.DatumAggregator;
import atl.space.components.render.RenderableComponent;
import atl.space.world.Scene;

public class Entity {
	
	private static boolean DEBUG = false;
	
	public String id;
	public Vector3f position;
	
	private Scene container = null;

	/**
	 * List of all components
	 */
	ArrayList<Component> components = null;
	//ArrayList<DatumAggregator> sensorSystems = null;
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
		//sensorSystems = new ArrayList<DatumAggregator>();
		if(DEBUG) System.out.println("Making Entity of ID: " + id +" at " + pos);
	}
	
	public Entity(Entity e) {
		this(e.id, new Vector3f(e.position), cloneComponentList(e.getComponents()));
		//TODO components?!?! Make sure this works
	}

	public Entity(String id, Vector3f position, List<Component> cs) {
		this(id, position);
		addComponents(cs);
	}
	
	public ArrayList<DatumAggregator> getSensorSystems() {
		//return sensorSystems;
		ArrayList<DatumAggregator> sensors = new ArrayList<DatumAggregator>();
		for(Component c: components){
			if(c instanceof DatumAggregator){
				sensors.add((DatumAggregator) c);
			}
		}
		return sensors;
	}
	
	//public void addSensor(DatumAggregator da) {
	//	sensorSystems.add(da);
	//	da.setOwnerEntity(this);
	//}
	
	
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
			
			if(DEBUG) System.out.println("DEBUG: No prereqs needed for " + component);
			
			/*if (component instanceof DatumAggregator) {
				if (sensorSystems.contains(component)) {
					throw new IllegalArgumentException("[WARN] System with name: " + component.getId() + " has already been added to entity " + this.id);
				}
				if (componentHash.containsKey(component.getId().toLowerCase())) {
					throw new IllegalArgumentException("[WARN] Component with name: " + component.getId() + " has already been added to entity " + this.id);
				}
				component.setOwnerEntity(this);
				components.add(component);
				componentHash.put(component.getId().toLowerCase(), component);
				sensorSystems.add((DatumAggregator)component);
			
			} else {*/
			
				if (componentHash.containsKey(component.getId().toLowerCase())) {
					throw new IllegalArgumentException("[WARN] Component with name: " + component.getId() + " has already been added to entity " + this.id);
				}
				component.setOwnerEntity(this);
				components.add(component);
				componentHash.put(component.getId().toLowerCase(), component);
		
			//}
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
		Vector3f.sub(target, getPosition(), temp);
		return temp.length();
	}

	public String getId() {
		return id;
	}
	
	public void setPosition(Vector3f position) {
		this.position = position;
	}

	public void update(int delta, Scene parent){
		for (Component component : components) {
			component.update(delta, parent);
		}
	}

	public void render() {
		for (Component component : components) {
			if (component.isRenderable()) {
				RenderableComponent rc = (RenderableComponent) component;
				rc.render();
			}
		}
	}
	public Entity getStepped(int delta, Scene sce) {
		Entity stepped = new Entity(id, position, components);
		stepped.update(delta, sce);
		return stepped;
	}
	
	public void setContainerEnvironment(Scene world){
		if(DEBUG){
			if(world==null){
				System.out.println("Attempting to add entity ("+this+") to null environment.");
			}
			if(container != null){
				System.out.println("Moving entity ("+this+") out of (" + container + ") to (" + world);
			}
		}
		container.removeEntity(this);
		container = world;
		container.addEntity(this);
	}
	
	public Scene getContainerEnvironment(){
		return container;
	}
	
	public String toString(){
		return "{"+ this.getId() + " at " + this.getPosition() + " with " + this.getComponents().size() + " components}";
	}

}
