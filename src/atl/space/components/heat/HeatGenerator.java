package atl.space.components.heat;


public interface HeatGenerator{
	public double heatToAdd();
	
	/*
	 * Sink represents where it'll put the heat. It could be itself.
	 */
	public HeatContainer getSink();
	public void setSink(HeatContainer sink);
	
	
	/*
	 * Make the heat, stick it somewhere
	 */
	public void generateHeat();
	
}

//not sure if I really need this actually
//Should generators come as heat containers, with their own internal heat storage? or no? or maybe only some of them?
//