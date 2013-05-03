package atl.space.data;

public abstract class Data {
	/*	
	 * 	I still don't really know if this is how I want to do this. Help?
	 */
	
	public abstract Object getData();
	public abstract boolean isLocationData();
	public abstract boolean isEntityData();
	public abstract boolean isBasicData();
	public abstract boolean isCommData();
}
