package atl.space.components.data.sensor;

import java.util.ArrayList;
import java.util.List;

import atl.space.components.data.DatumAggregator;
import atl.space.data.Data;
import atl.space.entities.Entity;

public class OmniscientRangedEntitySensor extends OmniscientEntitySensor implements
		DatumAggregator {
	
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
		//if(DEBUG) System.out.println(owner.getContainerEnvironment());
		
		List<Entity> tofilter = new ArrayList<Entity>(owner.getContainerEnvironment().getEntities());
		List<Entity> filtered = new ArrayList<Entity>();
		
		//if(DEBUG) System.out.println("[DEBUG] " + "Head");
		
		for(int i = 0; i < tofilter.size(); i++){
			Entity ent = tofilter.get(i);
			
			if( passesFilter(ent) ){
				filtered.add(ent);
				//if(DEBUG) System.out.println("[DEBUG] Added to filtered list");
			}
			else{
				//if(DEBUG) System.out.println("[DEBUG] Rejected from filtered list");
			}
		}
		//if(DEBUG) System.out.println(tofilter.size());
		return distillWantedData(filtered);
	}
	
	@SuppressWarnings("rawtypes")
	protected List<Data> distillWantedData(List<Entity> filtered){
		return Data.convertEntitiesToData(filtered);
	}
	
	protected boolean passesFilter(Entity ent){
		return owner.getDistance(ent) < maxDetectionRange;
	}


}
