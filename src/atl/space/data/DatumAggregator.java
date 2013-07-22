package atl.space.data;

import java.util.List;

import atl.space.entities.Entity;

public interface DatumAggregator {
	public DataType getDataType();
	public List<Data> getData();
	public void setOwnerEntity(Entity e);
}
