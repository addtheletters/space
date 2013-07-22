package atl.space.data;

import java.util.ArrayList;
import java.util.List;

import atl.space.components.Component;
import atl.space.entities.Entity;

public abstract class AbstractDatumExchangerComponent extends Component implements DatumAggregator{

	public AbstractDatumExchangerComponent(String id) {
		super(id);
	}

	@Override
	public Component clone() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<String> getPrerequisiteIDs(){
    	ArrayList<String> prids = new ArrayList<String>(1);
		prids.add("databank");
    	return prids;
    }

	@Override
	public DataType getDataType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Data> getData() {
		// TODO Auto-generated method stub
		return null;
	}

}
