package atl.space.components.data.sensor;

import java.util.List;

import atl.space.components.Component;
import atl.space.components.data.DatumAggregator;
import atl.space.data.Data;
import atl.space.data.DataType;

public class OmniscientEntitySensor extends Component implements
		DatumAggregator {
	
	public OmniscientEntitySensor(){
		super("OMNI(ENTITY_FULL)_SENSOR"); //for debugging, mostly
	}
	
	public OmniscientEntitySensor(String id) {
		super(id);
	}
	
	public OmniscientEntitySensor(OmniscientEntitySensor oes){
		super(oes.getId());
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
		return new OmniscientEntitySensor(this);
	}

}
