package atl.space.components;

@SuppressWarnings("serial")
public class PrerequisiteNotFoundException extends Exception {
	private String componentID;
	
	public PrerequisiteNotFoundException(String id){
		super("No component of ID: [" + id + "] found");
		this.componentID = id;
	}
	
	public String getComponentID(){
		return componentID;
	}
}
