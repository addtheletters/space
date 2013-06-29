package atl.space.rpg;

public class BlatantTextOption implements Option {
	
	private String longDescription;
	private String breifDescription;

	public BlatantTextOption(String breifDescr, String longDescr){
		this.longDescription = longDescr;
		this.breifDescription = breifDescr;
	}
	
	@Override
	public void applyConsequence() {
		System.out.println(getLongDescription());
	}
	
	@Override
	public String getDescription() {
		return breifDescription;
	}
	
	public String getLongDescription(){
		return longDescription;
	}
	
}
