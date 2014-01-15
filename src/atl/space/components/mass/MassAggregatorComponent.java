package atl.space.components.mass;

import java.util.ArrayList;
import java.util.List;

import atl.space.components.Component;

public class MassAggregatorComponent extends Component {
	
	/*
	 * This will be what other systems use to determine the mass of entities.
	 * For example, gravity will utilize this to determine calculatable mass.
	 * Each entity should only have one, or none, of these.
	 */
	
	public MassAggregatorComponent(){
		super("massaggregator");
	}
	
	public MassAggregatorComponent(MassAggregatorComponent mac){
		super(mac.getId());
	}
	
	public double getAggregateMass(){

		List<Component> massGivers = new ArrayList<Component>(owner.getComponents()); 
		//oh crap. I think components need to have more information tags.
		//We'll see what we can do without them for now.
		
		double aggregateMass = 0;
		
		for(Component c : massGivers){
			if(c instanceof Massive){
				aggregateMass += ((Massive)c).getMass();
			}
		}
		return aggregateMass;
	}
	
	@Override
	public Component clone() {
		return new MassAggregatorComponent(this);
	}

}
