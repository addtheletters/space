package atl.space.components.damage;

import java.util.List;

import atl.space.components.Component;
import atl.space.entities.Entity;

public abstract class DamageManagerComponent extends Component {
	
	//Is told how much and of what kind of damage the entity receives. 
	//If a certain component of the entity was targeted, it may result in different consequences.
	//Different Damage Managers for every ship configuration, so damage can be distributed accordingly. 
	//Various systems could be taken off line if targeted.
	
	public DamageManagerComponent(String id) {
		super(id);
	}
	
	public DamageManagerComponent(DamageManagerComponent dmc){
		super(dmc.getId());
	}

	public abstract void takeDamage(double damage, DamageType type);
	
	public abstract void takeDamage(double damage, DamageType type, Component target);
	
	@Override
	public void update(int delta, List<Entity> entities) {
		//do nothing
	}

}
