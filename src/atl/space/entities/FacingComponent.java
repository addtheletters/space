package atl.space.entities;

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
		Entity.restrictLength(facing, 1);
	}
	public FacingComponent(FacingComponent fc){
		id = fc.id;
		facing = fc.facing;
		Entity.restrictLength(facing, 1);
	}
	/*public void restrictLength(){
		if(facing.length() > 1 || facing.length() < -1){ //set length of the vector to 1
			facing.scale((float)(1/facing.length()));
		}
	}*/
	
	public void update(int delta) {
		Entity.restrictLength(facing, 1);
	}

	@Override
	public Component getStepped(int delta) {
		FacingComponent fc = new FacingComponent(this);
		fc.update(delta);
		return fc;
	}
	
	
}
