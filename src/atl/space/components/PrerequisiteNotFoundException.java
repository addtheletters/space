package atl.space.components;

@SuppressWarnings("serial")
public class PrerequisiteNotFoundException extends Exception {
	private String componentID;
	
	public PrerequisiteNotFoundException(List<String> id){
		super("No component(s) with ID(s): <" + id + "> found");
		this.componentID = id;
	}
	
	public String getComponentID(){
		return componentID;
	}
}
