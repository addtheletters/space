package atl.space.data;

public class Data<T> {
	/*	
	 * 	I still don't really know if this is how I want to do this. Help?
	 */
	
	//I could go for realism and have the data be in the form of a bytearray or something.
	//Seems like it'd be simpler to have various kinds of return and just have the processor
	//handle them differently based on the identifier.
	public T data;
	private String id;
	
	public Data (T data, String id) {
		this.data = data;
		this.id = id;
	}
	
	//public abstract DataType getType();
	
	public String getIdentifier(){
		return id;
	}
	

}
