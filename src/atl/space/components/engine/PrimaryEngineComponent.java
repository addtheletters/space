package atl.space.components.engine;


public class PrimaryEngineComponent extends AbstractEngineComponent {
	
	protected float thrust;
	protected float maxThrust;
	
	public PrimaryEngineComponent(float thrust, float maxThrust){
		super();
		this.thrust = thrust;
		this.maxThrust = maxThrust;
	}
	public PrimaryEngineComponent(float thrust){
		this(thrust, Float.MAX_VALUE);
	}
	public PrimaryEngineComponent(){
		this(0);
	}
	public PrimaryEngineComponent(PrimaryEngineComponent pec){
		super();
		this.thrust = pec.thrust;
		this.maxThrust = pec.maxThrust;
	}
	
	@Override
	public void applyThrust(){
		//prerequisite: facing component, acceleration component
		// TODO Auto-generated method stub

	}
	
	@Override
	public PrimaryEngineComponent clone() {
		return new PrimaryEngineComponent(this);
	}

}
