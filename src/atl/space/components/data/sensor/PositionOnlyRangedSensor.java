package atl.space.components.data.sensor;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.util.vector.Vector3f;

import atl.space.components.Component;
import atl.space.data.Data;
import atl.space.data.DataType;
import atl.space.entities.Entity;

public class PositionOnlyRangedSensor extends OmniscientRangedEntitySensor {

	float maxDetectionRange;

	public PositionOnlyRangedSensor(String id) {
		this(1000f);
	}

	public PositionOnlyRangedSensor(float detectionRange) {
		this("RANGED(POSITION_ONLY)_SENSOR", detectionRange);
	}

	public PositionOnlyRangedSensor(String id, float detectionRange) {
		super(id, detectionRange);
	}

	public PositionOnlyRangedSensor(PositionOnlyRangedSensor pors) {
		super(pors.id, pors.maxDetectionRange);
	}

	@Override
	public DataType getDataType() {
		return DataType.LOCATION;
	}

	@SuppressWarnings("rawtypes")
	protected List<Data> distillWantedData(List<Entity> filtered) {
		List<Data> data = new ArrayList<Data>();
		for (int i = 0; i < filtered.size(); i++) {
			data.add(new Data<Vector3f>(filtered.get(i).getPosition(),
					"LOCATION"));
		}
		return data;
	}

	@Override
	public Component clone() {
		return new PositionOnlyRangedSensor(this);
	}

}
