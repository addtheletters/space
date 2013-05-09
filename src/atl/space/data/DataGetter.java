package atl.space.data;

import java.util.List;
import java.util.Collection;

import atl.space.entities.Entity;

public interface DataGetter{
	
	public void enableData();
	public void disableData();
	public boolean dataEnabled();
	
	public Collection<Data> getData(List<Entity> worldEntities); //collections? pretty sure that's as abstract as I can go
	
	public String identifier();
	
	//DATACEPTION
	//There's data
	//in a data
	//in a data...
	
}
