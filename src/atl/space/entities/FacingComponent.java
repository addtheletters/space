package atl.space.entities;

import java.util.List;

import org.lwjgl.util.vector.Vector3f;

public class FacingComponent extends Component {
	public Vector3f facing;
	public FacingComponent(){
		id = "facing";
		facing = new Vector3f(0, 0, 0);
	}
	public FacingComponent(Vector3f f){
		id = "facing";
		facing = f;
		facing.normalise();
	}
	public FacingComponent(FacingComponent fc){
		id = fc.id;
		facing = fc.facing;
		facing.normalise();
		//Entity.restrictLength(facing, 1); //does the same thing as facing.normalise()
	}
	/*public void restrictLength(){
		if(facing.length() > 1 || facing.length() < -1){ //set length of the vector to 1
			facing.scale((float)(1/facing.length()));
		}
	}*/
	
	public void update(int delta, List<Entity> entities) {
		facing.normalise();
	}

	@Override
	public Component getStepped(int delta, List<Entity> entities) {
		FacingComponent fc = new FacingComponent(this);
		fc.update(delta, entities);
		return fc;
	}
	
	
}
