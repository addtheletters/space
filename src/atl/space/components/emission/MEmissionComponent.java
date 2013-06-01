package atl.space.components.emission;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.util.vector.Vector3f;

import atl.space.components.MovementComponent;
import atl.space.entities.Entity;

public abstract class MEmissionComponent extends EmissionComponent {
	//Creates emissions with the same movement as the owner
	
	public MEmissionComponent(String id){
		super(id);
	}
	
	public MEmissionComponent(MEmissionComponent mec){
		super(mec);
	}
	
	public List<String> getPrerequisiteIDs(){
    	ArrayList<String> prids = new ArrayList<String>(1);
		prids.add("movement");
    	return prids;
    }
	
	@Override
	public void trigger(List<Entity> entities) {
		Entity temp = buildEmission();
		temp.position = new Vector3f(owner.position);
		if(!temp.hasComponent("movement")){
			temp.addComponent(new MovementComponent((MovementComponent)owner.getComponent("movement")));
		}
		else{
			MovementComponent mc = (MovementComponent)temp.getComponent("movement");
			mc.speed = new Vector3f(((MovementComponent)owner.getComponent("movement")).speed);
		}
		entities.add(temp);
	}

	@Override
	protected Entity buildEmission() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected boolean canEmit() {
		// TODO Auto-generated method stub
		return false;
	}
	
}
