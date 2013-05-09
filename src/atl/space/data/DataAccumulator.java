package atl.space.data;

import java.util.ArrayList;
import java.util.Collection;


public abstract class DataAccumulator {
	
	private ArrayList<DataGetter> getters;
	
	public abstract Collection<Data> accumulate();
	
	
	//I feel the need to build a world class now -_-
	
}
