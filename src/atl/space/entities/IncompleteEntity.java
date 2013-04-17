package atl.space.entities;

public class IncompleteEntity extends Entity {
	
	//Not sure if this is needed, we'll see.
	
	public IncompleteEntity(Entity e) {
		super(e);
	}
	
	public void render(){
		//Get info from components and draw something that makes sense
		for (Component component : components) {
			//get relevant information
		}
		//render based on relevant information
	}

}
