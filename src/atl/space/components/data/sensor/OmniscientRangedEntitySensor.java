package atl.space.components.data.sensor;

import java.util.ArrayList;
import java.util.List;

import atl.space.components.data.DatumAggregator;
import atl.space.data.Data;
import atl.space.entities.Entity;

public class OmniscientRangedEntitySensor extends OmniscientEntitySensor implements
		DatumAggregator {
	
	private static boolean DEBUG = true;
	
	
	float maxDetectionRange;
	
	public OmniscientRangedEntitySensor(){
		this(1000f);
	}
	
	public OmniscientRangedEntitySensor(float detectionRange){
		this("OMNI_RANGED(ENTITY_FULL)_SENSOR", detectionRange);
	}
	
	public OmniscientRangedEntitySensor(String id, float detectionRange) {
		super(id);
		maxDetectionRange = detectionRange;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List<Data> getData() {
		List<Entity> tofilter = new ArrayList<Entity>(owner.getContainerEnvironment().getEntities());
		if(DEBUG) System.out.println("[DEBUG] " + "Head");
		
		for(int i = 0; i < tofilter.size(); i++){
			float temp = owner.getDistance(tofilter.get(i));
			
			if( temp > maxDetectionRange){
				tofilter.remove(i);
				if(DEBUG) System.out.println("[DEBUG] " + temp + " is > " + maxDetectionRange);
				//if(DEBUG) System.out.println("[DEBUG] " + tofilter.get(i).getPosition().length() + " is = " + owner.getDistance(tofilter.get(i)));
				//if(DEBUG) System.out.println("Removed an entity from list.");
			}
			else{
				if(DEBUG) System.out.println("[DEBUG] " + temp + " is < " + maxDetectionRange);
			}
		}
		//if(DEBUG) System.out.println(tofilter.size());
		return Data.convertEntitiesToData(tofilter);
	}


}
