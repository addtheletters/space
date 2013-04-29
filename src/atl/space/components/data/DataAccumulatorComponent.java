package atl.space.components.data;

import java.util.List;

import atl.space.components.Component;
import atl.space.entities.Entity;

public class DataAccumulatorComponent extends Component {

	public DataAccumulatorComponent(){
		id = "dataaccumulator";
	}
	public DataAccumulatorComponent(DataAccumulatorComponent dac){
		id = dac.getId();
	}
	
	/*
	 * Perhaps find out the information provided by all the data senders
	 * and build full entities with the component data?
	 */
	
	/* 
	 * public SOMETYPEOFDATAMAYBEANARRAYLIST<DataEntity> collectData(List<Entity> entities){
	 * 		SOMETYPEOFDATA<DataEntity> accum = new SOMETYPEOFDATA<DataEntity>();
	 * 		for(Component c : owner.components){
	 * 			if(c.id.equals("datasender") && c.isEnabled()){
	 * 				accum.add( (DataSenderComponent)c.getData(entities) );
	 * 			}
	 * 		}
	 * }
	 * 
	 */
	
	
	public DataAccumulatorComponent clone() {
		DataAccumulatorComponent temp = new DataAccumulatorComponent(this);
		return temp;
	}

	@Override
	public void update(int delta, List<Entity> entities) {
		// TODO Auto-generated method stub

	}

	@Override
	public Component getStepped(int delta, List<Entity> entities) {
		// TODO Auto-generated method stub
		return null;
	}

}
