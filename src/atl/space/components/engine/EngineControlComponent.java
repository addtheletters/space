package atl.space.components.engine;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.util.vector.Vector3f;

import atl.space.components.Component;

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
	
	public void fullForwards(){
		//TODO make this go
	}
	public void fullReverse(){
		//TODO make THIS go
	}
	public void maneuver(Vector3f dir){
		//TODO maneuvering thrusters going in dir
	}
	//more stuff?
	//combine this with turning for a full motion control something?
	
	@Override
	public Component clone() {
		return new EngineControlComponent(this);
	}

}
