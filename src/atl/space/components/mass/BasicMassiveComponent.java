package atl.space.components.mass;


import java.util.List;

import atl.space.components.Component;
import atl.space.entities.Entity;

public class BasicMassiveComponent extends Component implements Massive{
	
	private double mass;
	
	//This is more of a sample than something you would actually extend. I may end up deleting this later.
	
	public BasicMassiveComponent(){
		this(1);
	}
	
	public BasicMassiveComponent(double basicMass){
		super("partialmass");
		mass = basicMass;
	}
	
	public BasicMassiveComponent(BasicMassiveComponent bmc){
		super(bmc.getId());
		mass = bmc.getMass();
	}
	
	public double getMass(){
		return mass;
	}

	@Override
	public Component clone() {
		return new BasicMassiveComponent(this);
	}

	@Override
	public void update(int delta, List<Entity> entities) {
		//may be needed in subclasses
	}
	

}
