package atl.space.components.damage;

import java.util.ArrayList;
import java.util.List;

import atl.space.components.Component;
import atl.space.entities.Entity;

public abstract class DamageManagerComponent extends Component {
	
	//Is told how much and of what kind of damage the entity receives. 
	//If a certain component of the entity was targeted, it may result in different consequences.
	//Different Damage Managers for every ship configuration, so damage can be distributed accordingly. 
	//Various systems could be taken off line if targeted.
	
	private static final boolean DEBUG = true;
	
	public DamageManagerComponent(String id) {
		super(id);
	}
	
	public DamageManagerComponent(DamageManagerComponent dmc){
		super(dmc.getId());
	}

	
	//this will be overwritten, and super calls made to it
	public void takeDamage(double damage, DamageType type){
		if(DEBUG) System.out.println("DEBUG: Entity [" + owner + "] received " + damage + " damage of type " + type + ".");
	}
	
	//possibly same here
	public void takeDamage(double damage, DamageType type, Damageable_c target){
		if(DEBUG) System.out.println("DEBUG: Entity [" + owner + "] received " + damage + " damage of type " + type + " directed at component [" + target + "].");
	}
	
	public List<Damageable_c> getDamageableComponents(){
		List<Damageable_c> damageable = new ArrayList<Damageable_c>();
		for(Component c : owner.getComponents()){
			if(c instanceof Damageable_c){
				damageable.add((Damageable_c) c);
			}
		}
		return damageable;
	}
	
	
	@Override
	public void update(int delta, List<Entity> entities) {
		//do nothing
	}

}
