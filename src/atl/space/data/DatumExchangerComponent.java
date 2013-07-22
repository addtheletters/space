package atl.space.data;

import java.util.ArrayList;
import java.util.List;

import atl.space.components.Component;
import atl.space.entities.Entity;

@SuppressWarnings("rawtypes")
public class DatumExchangerComponent extends Component implements DatumAggregator{

	public DatumExchangerComponent(String id) {
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
		return DataType.LETS_SEE_HOW_YOU_HANDLE_THIS_SONIC;
	}

	@Override
	public List<Data> getData() {
		DataBank bank = (DataBank) owner.getComponent("databank");
		return bank.getData();
		
		//getting it ALL. Subclasses may only get some specific, or transfer what is needed
	}

	@Override
	public void update(int delta, List<Entity> entities) {
		// TODO Auto-generated method stub
		
	}

}
