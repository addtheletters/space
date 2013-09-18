package atl.space.components.data.sensor;

import java.util.ArrayList;
import java.util.List;

import atl.space.components.Component;
import atl.space.components.data.DatumAggregator;
import atl.space.data.Data;
import atl.space.data.DataType;
import atl.space.entities.Entity;

public class RandomTestSensor extends Component implements DatumAggregator {

	public RandomTestSensor(String id) {
		super(id);
		// TODO Auto-generated constructor stub
	}

	@Override
	public DataType getDataType() {
		return DataType.ARBITRARY;
	}

	@Override
	@SuppressWarnings("rawtypes")
	public List<Data> getData() {
		List<Data> data = new ArrayList<Data>();
		data.add(new Data<Integer>(1589, "idk either!"));
		data.add(new Data<Integer>( (int)(Math.random() * 10), "IDK!"));
		return data;
	}


	@Override
	public Component clone() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(int delta, List<Entity> entities) {
		
		
	}

}
