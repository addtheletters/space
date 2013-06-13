package atl.space.components.engine;

import java.util.ArrayList;
import java.util.List;

import atl.space.components.Component;
import atl.space.entities.Entity;

public class EngineControlComponent extends Component {

	public EngineControlComponent(){
		super("enginecontrol");
	}
	
	public EngineControlComponent(EngineControlComponent ecc){
		super(ecc.getId());
	}
	
	public List<AbstractEngineComponent> getEngines(){
		List<AbstractEngineComponent> engines = new ArrayList<AbstractEngineComponent>();
		for(Component c : owner.getComponents()){
			if(c instanceof AbstractEngineComponent){
				engines.add((AbstractEngineComponent)c);
			}
		}
		return engines;
	}
	
	//#thatsmorelikeit
	
	@Override
	public Component clone() {
		return new EngineControlComponent(this);
	}

	@Override
	public void update(int delta, List<Entity> entities) {
		// do nothing?
	}

}
