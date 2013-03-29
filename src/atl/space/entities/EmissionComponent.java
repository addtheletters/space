package atl.space.entities;

import java.util.List;

public class EmissionComponent extends Component implements Triggerable {
	public Entity emission = new Entity("defaultEmission", owner.position);
	
	public EmissionComponent(Entity emission){
		id = "emission";
		this.emission = emission;
	}
	public EmissionComponent(EmissionComponent ec){
		id = ec.id;
		emission = ec.emission;
	}
	@Override
	public void trigger(List<Entity> entities) {
		Entity temp = new Entity(emission);
		entities.add(temp);
	}

	@Override
	public void update(int delta, List<Entity> entities) {
		//do nothing
	}

	@Override
	public Component getStepped(int delta, List<Entity> entities) {
		EmissionComponent ec = new EmissionComponent(this);
		ec.update(delta, entities);
		return ec;
	}

}
