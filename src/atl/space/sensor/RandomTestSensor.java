package atl.space.sensor;

import java.util.List;

import atl.space.components.Component;
import atl.space.data.Data;
import atl.space.data.DataType;
import atl.space.data.DatumAggregator;
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
	public Data getData() {
		return new Data<Integer>(4,"idk");
	}

	@Override
	public void setOwnerEntity(Entity e) {
		// TODO Auto-generated method stub

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
