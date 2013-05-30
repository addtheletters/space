package atl.space.components;

import java.util.List;

@SuppressWarnings("serial")
public class PrerequisiteNotFoundException extends Exception {
	private List<String> componentIDs;
	
	public PrerequisiteNotFoundException(List<String> ids){
		super("No component(s) with ID(s): <" + ids + "> found");
		this.componentIDs = ids;
	}
	
	public List<String> getComponentIDs(){
		return componentIDs;
	}
}
