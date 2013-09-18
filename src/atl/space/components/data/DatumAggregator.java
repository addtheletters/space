package atl.space.components.data;

import java.util.List;

import atl.space.data.Data;
import atl.space.data.DataType;


@SuppressWarnings("rawtypes")
public interface DatumAggregator {
	public DataType getDataType(); //
	public List<Data> getData();
	//public void setOwnerEntity(Entity e); //since we are using components
}
