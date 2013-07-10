package atl.space.data;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import atl.space.components.Component;
import atl.space.components.render.RenderableComponent;
import atl.space.entities.Entity;

public class DataBank extends Component {

	public DataBank() {
		super("DataBank");
		dataList = new ArrayList<Data>();
	}
	
	ArrayList<Data> dataList;
	//TreeMap<DataType,ArrayList<Data>> hashedData;
	public void accumulateData() {
		System.out.println(owner.getSensorSystems());
		for (DatumAggregator da: owner.getSensorSystems() ) {
			dataList.add(da.getData());
			//if (hashedData.get(da.getDataType())==null) {
			//		tmp = new 
			//}
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
	public void update(int delta, List<Entity> entities) {
		// TODO Auto-generated method stub
		accumulateData();
		System.out.println(dataList);
	}

}
