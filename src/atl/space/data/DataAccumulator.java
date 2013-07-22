package atl.space.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import atl.space.entities.Entity;

//deprecated. Kinda replacement: DataBank

public abstract class DataAccumulator {
	
	private static final boolean DEBUG = true;
	
	private List<DataGetter> getters;
	
	@SuppressWarnings("rawtypes")
	public Collection<Data> accumulate(List<Entity> worldEntities){
		List<Data> tempData = new ArrayList<Data>();
		Iterator<DataGetter> it = getters.iterator();
		while(it.hasNext()){
			DataGetter tempGetter = it.next();
			if(tempGetter.isEnabled()){
				tempData.addAll(tempGetter.getData(worldEntities));
			}
			if(DEBUG){
				System.out.println(tempGetter);
			}
		}
		return tempData;
	}
	
	public void addGetter(DataGetter dgtr){
		getters.add(dgtr);
	}
	
	
	//I feel (the need)#cross the urge to build a world class now -_-
	//Prolly need various processors too for these. A "user drawing/render processor," "AI processor," etc
	
}
