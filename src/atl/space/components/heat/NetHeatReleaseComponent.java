package atl.space.components.heat;

import java.util.List;

import atl.space.components.Component;
import atl.space.entities.Entity;

public class NetHeatReleaseComponent extends Component {

	/*
	 * Sums up heat release so sensors can detect it easily
	 */
	
	double totalrelease;
	
	public NetHeatReleaseComponent(){
		super("heat");
	}
	
	public NetHeatReleaseComponent(NetHeatReleaseComponent nhc){
		super(nhc.getId());
	}

	@Override
	public Component clone() {
		// TODO Auto-generated method stub
		return new NetHeatReleaseComponent(this);
	}

	@Override
	public void update(int delta, List<Entity> entities) {
		// Do nothing		
	}
	
	
	
}
