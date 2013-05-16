package atl.space.components.heat;


public interface HeatGenerator{
	public double getHeatToAdd();

}

//not sure if I really need this actually
//Should generators come as heat containers, with their own internal heat storage? or no? or maybe only some of them?
//