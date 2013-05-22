package atl.space.entities;

import java.util.ArrayList;
import java.util.List;

import atl.space.components.Component;
import atl.space.components.render.RenderableComponent;

/*
 * probably deprecated along with atl.space.components.data
 */
public class DataEntity extends Entity {
	
	//Not sure if this is needed, we'll see.
	
	//For things we're actually rendering, but not the actual data of the "real" entity it represents
	ArrayList<Component> concreteComponents = null; //ArrayList<RenderableComponent>?
	//For example. This could have an overlay2drendercomponent that displays an "anomaly" symbol
	//if all the data we have is a thermal or electromagnetic disturbance
	
	
	public DataEntity(Entity e) {
		super(e);
	}
	public DataEntity(DataEntity e){
		super(e);
		concreteComponents = cloneComponentList(e.concreteComponents);
	}
	
	public void render(){
		//Get info from components and draw something that makes sense
		
		//for (Component component : components) {
			//get relevant information
		//}
		//Update concretes based on relevant information
		//Maybe does some more stuff like extra rendering
		for (Component component : concreteComponents) {
			if (component.isRenderable()) {
				RenderableComponent rc = (RenderableComponent) component;
				rc.render();
			}
		}
	}
	
	public void addConcreteComponent(Component component) {
		component.setOwnerEntity(this);
		concreteComponents.add(component);
	}
	
	public Component getConcreteComponent(String componentID){
		for (Component comp : concreteComponents) {
			if (comp.getId().equalsIgnoreCase(id))
				return comp;
		}
		return null;
	}
	
	public List<Component> getConcreteComponents(){
		return concreteComponents;
	}
	
	public void update(int delta, List<Entity> entities) {
		//do nothing?
		//Overrides Entity's update
	}
	

}
