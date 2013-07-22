package atl.space.data;

import java.util.List;


@SuppressWarnings("rawtypes")
public interface DatumAggregator {
	public DataType getDataType(); //
	public List<Data> getData();
	//public void setOwnerEntity(Entity e); //since we are using components
}
