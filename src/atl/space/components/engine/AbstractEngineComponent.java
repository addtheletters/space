package atl.space.components.engine;

import java.util.List;

import atl.space.components.Component;
import atl.space.entities.Entity;

public abstract class AbstractEngineComponent extends Component {
	
	public AbstractEngineComponent(){
		super("engine");
	}
	
	
	public abstract void applyThrust();
	

	@Override
	public void update(int delta, List<Entity> entities) {
		applyThrust();
	}

}
