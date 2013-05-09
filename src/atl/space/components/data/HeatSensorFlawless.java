package atl.space.components.data;
//--require('HEAT')
//--awwww. Syntax errors!?!!?!? freakin lua bleargh


/*
 * deprecated, replacement in progress in atl.space.data
 */
public class HeatSensorFlawless extends DataSenderComponent { //haha flawless haha ***SYNTAX ERROR***
	//...these comments will probably confuse a lot of people ah well
	
	/*--psst hey I can dos the luas
	 * function doEverything()
	 * everything = alreadyexistingThingThatDoesEverything:doEverything()
	 * 	return everything
	 * end
	 * 
	*/
	
	/*//pseudocodes
	 * public SOMETYPEOFDATAMAYBEANARRAYLIST<DataEntity> getData(List<Entity> entitiesInTheWorld){
	 *	//HEY GUY! HEY!	
	 *		if(!enabled){
	 *			System.err.println("Tried to access non-enabled data sender");
	 *		}
	 *		List<DataEntity> ret = new ArrayList<DataEntity>();
	 *		for(Entity e : entitiesInTheWorld){
	 *			if(owner.getDistance(e.getPosition()) < detectionRange || e.heatSignature > detectableHeatSig ){
	 *				DataEntity temp = new DataEntity();
	 *				temp.addComponent(heatComponent || heatEmissionsDataThingy);
	 *				ret.add(temp)
	 *			}
	 *		}
	 * }
	 */
	
	
	public HeatSensorFlawless(HeatSensorFlawless hsf){
		id = hsf.getId();
	}
	
	@Override
	public HeatSensorFlawless clone() {
		HeatSensorFlawless temp = new HeatSensorFlawless(this);
		return temp;
	}
	
	
}
