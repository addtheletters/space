package atl.space.components.heat;

import atl.space.components.Component;

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
	
}
