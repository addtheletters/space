package atl.space.components.mass;

import java.util.ArrayList;
import java.util.List;

import atl.space.components.Component;

public class BasicMassiveComponent extends Component implements Massive {

	private double mass;

	// This is more of a sample than something you would actually extend. I may
	// end up deleting this later.

	public BasicMassiveComponent() {
		this(1);
	}

	public BasicMassiveComponent(double basicMass) {
		super("partialmass");
		mass = basicMass;
	}

	public BasicMassiveComponent(BasicMassiveComponent bmc) {
		super(bmc.getId());
		mass = bmc.getMass();
	}
	
	public List<String> getPrerequisiteIDs(){
    	ArrayList<String> prids = new ArrayList<String>(1);
		prids.add("massaggregator");
    	return prids;
    }
	
	public double getMass() {
		return mass;
	}

	@Override
	public Component clone() {
		return new BasicMassiveComponent(this);
	}

}
