package atl.space.components.accel;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.util.vector.Vector3f;

import atl.space.components.Component;
import atl.space.components.MovementComponent;
import atl.space.entities.Entity;

public class NetAccelComponent extends Component {
	/*
	 * Replaces old AccelComponent. Adds together the thrust of all components which provide acceleration
	 * and actually applies their acceleration effect.
	 */
	
	private static boolean DEBUG = false;
	
	public NetAccelComponent(){
		super("accel");
	}
	
	public NetAccelComponent(NetAccelComponent nac){
		super(nac.getId());
	}
	
	public List<String> getPrerequisiteIDs(){
    	ArrayList<String> prids = new ArrayList<String>(1);
		prids.add("movement");
    	return prids;
    }
	
	public Vector3f getNetAccel(int delta, List<Entity> entities){

		List<Component> accelGivers = new ArrayList<Component>(owner.getComponents()); 
		
		Vector3f netAccel = new Vector3f();
		
		for(Component c : accelGivers){
			if(c instanceof AccelProvider){
				Vector3f toAdd = ((AccelProvider)c).getAccel(delta, entities);
				if(DEBUG) System.out.println("DEBUG: Adding " + toAdd + " to net accel");
				Vector3f.add(netAccel, toAdd, netAccel);
			}
		}
		return netAccel;
	}
	
	@Override
	public Component clone() {
		return new NetAccelComponent(this);
	}

	@Override
	public void update(int delta, List<Entity> entities) {
		MovementComponent mc = (MovementComponent)owner.getComponent("movement");
		Vector3f.add(mc.speed, getNetAccel(delta, entities), mc.speed);
	}
}
