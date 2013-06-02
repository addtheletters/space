package atl.space.data;

import java.util.List;
import java.util.Collection;

import atl.space.components.Enableable;
import atl.space.entities.Entity;

public interface DataGetter extends Enableable{
	
	
	public Collection<Data> getData(List<Entity> worldEntities); //collections? pretty sure that's as abstract as I can go
	
	public String identifier();
	
	//DATACEPTION
	//There's data
	//in a data
	//in a data...
	
}
