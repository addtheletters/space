package atl.space.components.heat;

import atl.space.components.Component;

public abstract class HeatGeneratorComponent extends Component implements HeatGenerator {
	
	private HeatContainer sinkReference;
	
	public HeatGeneratorComponent() {
		this(null);
	}
	
	public HeatGeneratorComponent(HeatContainer sinkRef){
		super("heatgenerator");
		this.sinkReference = sinkRef;
	}

	public abstract double heatToAdd();

	@Override
	public HeatContainer getSink() {
		return sinkReference;
	}
	
	@Override
	public void setSink(HeatContainer sink){
		sinkReference = sink;
	}

	@Override
	public void generateHeat() {
		getSink().addHeat(heatToAdd());
	}

}
