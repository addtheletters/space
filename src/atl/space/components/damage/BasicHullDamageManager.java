package atl.space.components.damage;

import java.util.ArrayList;
import java.util.List;

import atl.space.components.Component;

public class BasicHullDamageManager extends DamageManagerComponent {
	
	public BasicHullDamageManager() {
		super("basichulldamagemanager");
	}
	
	public BasicHullDamageManager(BasicHullDamageManager bhdm){
		super(bhdm);
	}
	
	public List<String> getPrerequisiteIDs(){
    	ArrayList<String> prids = new ArrayList<String>(1);
		prids.add("basichullintegrity");
    	return prids;
    }
	
	@Override
	public void takeDamage(double damage, DamageType type) {
		BasicHullIntegrityComponent temp = (BasicHullIntegrityComponent)owner.getComponent("basichullintegrity");
		temp.addIntegrity(damage * -1);
		super.takeDamage(damage, type); //debug message
	}

	@Override
	public void takeDamage(double damage, DamageType type, Component target) {
		takeDamage(damage, type);
	}

	@Override
	public Component clone() {
		return new BasicHullDamageManager(this);
	}

}
