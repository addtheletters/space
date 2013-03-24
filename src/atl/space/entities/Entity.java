package atl.space.entities;

import java.util.List;
import java.util.ArrayList;

import org.lwjgl.util.vector.Vector3f;

public class Entity {

	public String id;
	public Vector3f position;

	ArrayList<Component> components = null;
	//renderable components in this list
	
	
	public static void restrictLength(Vector3f v, float length){
		v.normalise();
		v.scale(length);
	}
	
	public Entity(String id) {
		//System.out.println("Instantiate!");
		this.id = id;
		components = new ArrayList<Component>();
		position = new Vector3f(0, 0, 0);
	}

	public Entity(String id, Vector3f position, ArrayList<Component> cs) {
		this.id = id;
		this.position = position;
		components = cs;
		// addComponents(cs);
	}

	public void addComponent(Component component) {
		component.setOwnerEntity(this);
		components.add(component);
		//System.out.println("Add!");
	}

	public void addComponents(List<Component> cs) {
		for (Component c : cs) {
			addComponent(c);
		}
	}

	public Component getComponent(String id) {
		for (Component comp : components) {
			if (comp.getId().equalsIgnoreCase(id))
				return comp;
		}

		return null;
	}

	public boolean hasComponent(String id) {
		for (Component comp : components) {
			if (comp.getId().equalsIgnoreCase(id))
				return true;
		}

		return false;
	}

	public Vector3f getPosition() {
		return position;
	}

	public String getId() {
		return id;
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