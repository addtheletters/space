package atl.space.components.data.sensor;

import java.util.List;

import atl.space.components.Component;
import atl.space.data.Data;
import atl.space.data.DataType;
import atl.space.data.DatumAggregator;
import atl.space.entities.Entity;

public class OmniscientEntitySensor extends Component implements
		DatumAggregator {
	
	public OmniscientEntitySensor(){
		super("OMNI(ENTITY_FULL)_SENSOR");
	}
	
	public OmniscientEntitySensor(String id) {
		super(id);
	}

	@Override
	public DataType getDataType() {
		return DataType.ENTITY_FULL;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List<Data> getData() {
		return Data.convertEntitiesToData(owner.getContainerEnvironment().getEntities());
	}

	@Override
	public Component clone() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(int delta, List<Entity> entities) {
		// TODO Auto-generated method stub

	}

}
