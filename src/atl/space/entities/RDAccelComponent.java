package atl.space.entities;


public class RDAccelComponent extends DAccelComponent {
	public float maxAccelForward;
	public float maxAccelBack;
	public float maxAccelSecondary;

	public RDAccelComponent() {
		super();
		maxAccelForward = 0;
		maxAccelBack = 0;
		maxAccelSecondary = 0;
	}

	public RDAccelComponent(float maf, float mab, float mas) {
		super();
		maxAccelForward = maf;
		maxAccelBack = mab;
		maxAccelSecondary = mas;
	}
	
	
	public RDAccelComponent(RDAccelComponent rdac){
		super(rdac);
		maxAccelForward = rdac.maxAccelForward;
		maxAccelBack = rdac.maxAccelBack;
		maxAccelSecondary = rdac.maxAccelSecondary;
	}

	public void update(int delta) {
		checkMaxes();
		super.update(delta);
	}

	public Component getStepped(int delta) {
		RDAccelComponent rac = new RDAccelComponent(this);
		rac.update(delta);
		return rac;
	}
	public void checkMaxes(){
		if(accelForward > maxAccelForward){
			accelForward = maxAccelForward;
		}
		if(accelBack > maxAccelBack){
			accelBack = maxAccelBack;
		}
		if(Math.abs(accelSecondary) > maxAccelSecondary){
			accelSecondary = maxAccelSecondary;
		}
	}
	
}
