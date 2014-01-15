package atl.space.components.data;

import java.util.ArrayList;

import atl.space.components.Component;
import atl.space.data.Data;
import atl.space.world.Scene;

@SuppressWarnings("rawtypes")
public class DataBank extends Component {

	ArrayList<Data> dataList;
	
	public DataBank() {
		super("databank"); // component IDs are lowercased for less confusion
		dataList = new ArrayList<Data>();
	}
	
	public void accumulateData() {
		dataList = new ArrayList<Data>();
		for (DatumAggregator da: owner.getSensorSystems() ) {
			dataList.addAll(da.getData());
		}
	}
	
	public ArrayList<Data> getData() {
			return dataList;
	}
	
	@Override
	public Component clone() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void update(int delta, Scene sce) {
		accumulateData();
	}
	

}
