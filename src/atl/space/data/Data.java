package atl.space.data;

public abstract class Data {
	/*	
	 * 	I still don't really know if this is how I want to do this. Help?
	 */
	
	//I could go for realism and have the data be in the form of a bytearray or something.
	//Seems like it'd be simpler to have various kinds of return and just have the processor
	//handle them differently based on the identifier.
	public Object data;
	
	/*
	 * I guess we can keeps da boolean craps...
	 */
	//public abstract boolean isLocationData();
	//public abstract boolean isEntityData();
	//public abstract boolean isBasicData();
	//public abstract boolean isCommData();
	
	/*
	 * replace the boolean craps?
	 */
	
	public String identifier(){
		return "UNIDENTIFIED";
	}
	

}
