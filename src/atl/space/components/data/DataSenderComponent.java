package atl.space.components.data;

import java.util.List;

import atl.space.components.Component;
import atl.space.components.Enableable;
import atl.space.entities.Entity;

public class DataSenderComponent extends Component implements Enableable{
	//this might wanna be abstract actually
	
	//sends data
	//Not sure in what form.
	//A list of entities perhaps. 
	//Or maybe one of those maps n stuff Zach was talking about.
	//But if it's a list of entities, it would give the positions of
	//the entities and not really need any numbers for distances
	//I feel like maybe an extension for Entity would be in order,
	//like an DataEntity, which can have indefinite components to account for sensors
	//not being 100% accurate or not providing every little bit of info about an object
	
	//Scanners
	//Sensors
	//Communications
	
	private boolean enabled;
	
	public DataSenderComponent(){
		id = "datasender";
	}
	public DataSenderComponent(DataSenderComponent dsc){
		id = dsc.getId();
	}
	
	/*
	 * public SOMETYPEOFDATAMAYBEANARRAYLIST<DataEntity> getData(List<Entity> entitiesInTheWorld){
	 *	//HEY GUY! HEY!	
	 *		if(!enabled){
	 *			System.err.println("Tried to access non-enabled data sender");
	 *		}
	 * }
	*/
	
	
	public boolean isEnabled(){
		return enabled;
	}
	
	public void setEnabled(boolean b){
		enabled = b;
	}
	
	public void disable(){
		enabled = false;
	}
	
	public void enable(){
		enabled = true;
	}
	
	@Override
	public DataSenderComponent clone() {
		DataSenderComponent temp = new DataSenderComponent(this);
		return temp;
	}

	@Override
	public void update(int delta, List<Entity> entities) {
		// TODO do nothing? depends on how accumulators and such are implemented
	}

	@Override
	public Component getStepped(int delta, List<Entity> entities) {
		// TODO Maybe implement this, maybe not
		return null;
	}
	@Override
	public void trigger(List<Entity> entities) {
		enabled = true;
	}
	
	//Data
	
	
}
