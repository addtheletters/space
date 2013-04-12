package atl.space.entities;

import java.util.List;

import org.lwjgl.util.vector.Vector3f;

public class EmissionComponent extends Component implements Triggerable {
	public Entity emission;
	public EmissionComponent(){
		id = "emission";
		emission = new Entity("defaultEmission");
	}
	
	public EmissionComponent(Entity emission){
		id = "emission";
		this.emission = emission;
	}
	public EmissionComponent(EmissionComponent ec){
		id = "emission";
		emission = ec.emission;
	}
	
	public EmissionComponent clone() {
		return new EmissionComponent(this);
	}
	@Override
	public void trigger(List<Entity> entities) {
		Entity temp = new Entity(emission);
		temp.position = new Vector3f(owner.position);
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
