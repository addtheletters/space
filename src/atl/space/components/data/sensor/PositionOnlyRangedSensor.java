package atl.space.components.data.sensor;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.util.vector.Vector3f;

import atl.space.components.Component;
import atl.space.components.data.DatumAggregator;
import atl.space.data.Data;
import atl.space.data.DataType;
import atl.space.entities.Entity;

public class PositionOnlyRangedSensor extends Component implements
		DatumAggregator {

	float maxDetectionRange;
	
	public PositionOnlyRangedSensor(String id) {
		this(1000f);
	}
	
	public PositionOnlyRangedSensor(float detectionRange){
		this("RANGED(POSITION_ONLY)_SENSOR", detectionRange);
	}

	public PositionOnlyRangedSensor(String id, float detectionRange){
		super(id);
		this.maxDetectionRange = detectionRange;
	}
	
	public PositionOnlyRangedSensor(PositionOnlyRangedSensor pors){
		super(pors.id);
		this.maxDetectionRange = pors.maxDetectionRange;
	}
	
	@Override
	public DataType getDataType() {
		return DataType.LOCATION;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List<Data> getData() {
		List<Entity> tofilter = new ArrayList<Entity>(owner.getContainerEnvironment().getEntities());
		List<Data> filtered = new ArrayList<Data>();
		
		for(int i = 0; i < tofilter.size(); i++){
			Entity ent = tofilter.get(i);
			float temp = owner.getDistance(ent);
			if( temp < maxDetectionRange){
				filtered.add( new Data<Vector3f>( ent.getPosition(), "LOCATION") );
			}
			else{
				//if(DEBUG) System.out.println("[DEBUG] Rejected from filtered list");
			}
		}
		return filtered;
	}

	
	@Override
	public void update(int delta, List<Entity> entities) {
		// Do nothing
	}

	@Override
	public Component clone() {
		return new PositionOnlyRangedSensor(this);
	}

}
