package atl.space.components.data;


/*
 * deprecated, replacement in progress in atl.space.data
 */
public class OmniscientSensorComponent extends DataSenderComponent {
	
	public OmniscientSensorComponent(){
		id = "datasender";
	}
	public OmniscientSensorComponent(OmniscientSensorComponent osc){
		id = osc.getId();
	}
	
	public OmniscientSensorComponent clone(){
		OmniscientSensorComponent temp = new OmniscientSensorComponent(this);
		return temp;
	}
	
	
	
	/*
	 * public SOMETYPEOFDATAMAYBEANARRAYLIST<DataEntity> getData(List<Entity> entitiesInTheWorld){
	 *		if(!enabled){
	 *			System.err.println("Tried to access non-enabled data sender");
	 *		}
	 *		
	 *		//Return all available data in entitiesInTheWorld	
	 * 
	 * }
	*/
}
