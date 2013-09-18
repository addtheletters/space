package atl.space.data;

import java.util.ArrayList;
import java.util.List;

import atl.space.entities.Entity;

/*
 * Class for Data of all types.
 * Also contains utility methods for manipulating, displaying, and converting data.
 */
public class Data<T> {
	public T data;
	private String id;
	
	public Data (T data, String id) {
		this.data = data;
		this.id = id;
	}
	
	public String getIdentifier(){
		return id;
	}
	
	@SuppressWarnings("rawtypes")
	public static List<Data> convertEntitiesToData(List<Entity> entities){
		List<Data> converted = new ArrayList<Data>();
		for(int i = 0; i < entities.size(); i++){
			converted.add(new Data<Entity>(entities.get(i), "ENTITY_FULL"));
		}
		return converted;
	}

}
