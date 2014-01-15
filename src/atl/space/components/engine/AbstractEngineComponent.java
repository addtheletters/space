package atl.space.components.engine;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.util.vector.Vector3f;

import atl.space.components.Component;
import atl.space.components.linearmotion.accel.AccelProvider;
import atl.space.entities.Entity;
import atl.space.world.Scene;

public abstract class AbstractEngineComponent extends Component implements AccelProvider{
	
	public AbstractEngineComponent(){
		super("engine");
	}
	public AbstractEngineComponent(String id){
		super(id);
	}
	
	public List<String> getPrerequisiteIDs(){
    	ArrayList<String> prids = new ArrayList<String>(1);
		prids.add("accel");
    	return prids;
    }
	
	public abstract Vector3f getThrust();
	
	public abstract void setThrustForce(float thrust);
	
	@Override
	public Vector3f getAccel(int delta, Scene sce) {
		return getThrust();
	}
	
	public void update(int delta, List<Entity> entities) {
		//do nothing
	}

}
