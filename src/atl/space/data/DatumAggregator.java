package atl.space.data;

import atl.space.entities.Entity;

public interface DatumAggregator {
	public DataType getDataType();
	public Data getData();
	public void setOwnerEntity(Entity e);
}
