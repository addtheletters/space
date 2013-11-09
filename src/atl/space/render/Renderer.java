package atl.space.render;

import atl.space.data.Data;
import atl.space.data.DataType;
import atl.space.entities.Entity;

/**
 * Replacement for render components, with the intent of making rendering more universal
 * and workable for entities of all types and complexities.
 * @author Ben
 */
public class Renderer {
	public void renderEntity(Entity e){
		//differentiate based on entity ID
		//differentiate based on identified components
		//TODO this
	}
	public void renderData(Data d){
		if(typeIsRenderable( d.getIdentifier() ) ){
		//TODO this	
		}
	}
	
	public static boolean typeIsRenderable(String identifier ){
		//TODO this
		return false;
	}
}
