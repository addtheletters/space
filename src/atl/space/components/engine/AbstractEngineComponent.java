package atl.space.components.engine;

import java.util.ArrayList;
import java.util.List;

import atl.space.components.Component;
import atl.space.entities.Entity;

public abstract class AbstractEngineComponent extends Component {
	
	public AbstractEngineComponent(){
		super("engine");
	}
	
	public List<String> getPrerequisiteIDs(){
    	ArrayList<String> prids = new ArrayList<String>(1);
		prids.add("accel");
    	return prids;
    }
	
	public abstract void applyThrust();
	

	@Override
	public void update(int delta, List<Entity> entities) {
		applyThrust();
	}

}
