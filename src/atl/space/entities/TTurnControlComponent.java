package atl.space.entities;

import java.util.List;

public class TTurnControlComponent extends TurnControlComponent {
	//Never loses "lock"
	public void update(int delta, List<Entity> entities){
		turningToTarget = true;
		super.update(delta, entities);
	}
}
