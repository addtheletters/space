package atl.space.components.damage;

import java.util.List;

import atl.space.components.Component;
import atl.space.entities.Entity;

public class BasicHullIntegrityComponent extends Component {
	
	private static final double DEFAULT_START_INTEGRITY = 100;
	private double integrity;
	
	public BasicHullIntegrityComponent(String id) {
		super(id);
		integrity = DEFAULT_START_INTEGRITY;
	}
	
	public BasicHullIntegrityComponent(double integrity){
		super("basichullintegrity");
		this.integrity = integrity;
	}
	
	public BasicHullIntegrityComponent(BasicHullIntegrityComponent bhic){
		super(bhic.getId());
		integrity = bhic.getIntegrity();
	}
	
	public void addIntegrity(double amount){
		integrity += amount;
	}
	
	@Override
	public Component clone() {
		return new BasicHullIntegrityComponent(this);
	}
	
	public boolean isIntact(){
		return integrity > 0;
	}
	
	public double getIntegrity(){
		return integrity;
	}
	
	@Override
	public void update(int delta, List<Entity> entities) {
		if(integrity < 0) integrity = 0;
	}

}
