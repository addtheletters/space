package atl.space.components.angularmotion.old;

import java.util.List;

import atl.space.components.Component;
import atl.space.entities.Entity;

public class TTurnControlComponent extends TurnControlComponent {
	//Never loses "lock"
	public TTurnControlComponent(){
		super();
	}
	public TTurnControlComponent(TTurnControlComponent ttcc){
		super(ttcc);
	}
	public Component clone(){
		return new TTurnControlComponent(this);
	}
	public void update(int delta, List<Entity> entities){
		turningToTarget = true;
		super.update(delta, entities);
	}
}
