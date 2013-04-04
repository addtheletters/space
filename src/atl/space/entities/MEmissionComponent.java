package atl.space.entities;

import java.util.List;

public class MEmissionComponent extends EmissionComponent {
	//Creates emissions with the same movement as the owner
	
	public MEmissionComponent(){
		super();
	}
	public MEmissionComponent(Entity emission){
		super(emission);
	}
	public MEmissionComponent(MEmissionComponent mec){
		super(mec);
	}
	
	@Override
	public void trigger(List<Entity> entities) {
		Entity temp = new Entity(emission);
		if(!temp.hasComponent("movement")){
			temp.addComponent(new MovementComponent((MovementComponent)owner.getComponent("movement")));
		}
		else{
			MovementComponent mc = (MovementComponent)temp.getComponent("movement");
			mc.speed = ((MovementComponent)owner.getComponent("movement")).speed;
		}
		entities.add(temp);
	}


	@Override
	public Component getStepped(int delta, List<Entity> entities) {
		MEmissionComponent ec = new MEmissionComponent(this);
		ec.update(delta, entities);
		return ec;
	}
	
}
