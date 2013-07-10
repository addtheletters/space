package atl.space.sensor;

import atl.space.data.Data;
import atl.space.data.DataType;
import atl.space.data.DatumAggregator;
import atl.space.entities.Entity;

public class RandomTestSensor implements DatumAggregator {

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

}
