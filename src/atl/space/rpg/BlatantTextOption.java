package atl.space.rpg;

public class BlatantTextOption implements Option {
	
	private String longDescription;
	private String breifDescription;
	private int nextEvent;
	
	public BlatantTextOption(String breifDescr, String longDescr, int nextEvent){
		this.longDescription = longDescr;
		this.breifDescription = breifDescr;
		this.nextEvent = nextEvent;
	}
	
	@Override
	public void applyConsequence() {
		System.out.println(getLongDescription());
	}
	
	public int getNextEventIndex(){
		return nextEvent;
	}
	
	@Override
	public String getDescription() {
		return breifDescription;
	}
	
	public String getLongDescription(){
		return longDescription;
	}
	
}
