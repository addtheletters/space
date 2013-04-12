package atl.space.entities;

import java.util.List;

import org.lwjgl.util.vector.Vector3f;

public class RTurningComponent extends TurningComponent {
	public float turnAbility;
	public RTurningComponent(){
		super();
		turnAbility = 0;
	}
	public RTurningComponent(Vector3f tv, float ta){
		super(tv);
		turnAbility = ta;
		Entity.restrictLength(turn, turnAbility);
	}
	public RTurningComponent(RTurningComponent rtc){
		super(rtc.turn);
		turnAbility = rtc.turnAbility;
		Entity.restrictLength(turn, turnAbility);
	}
	
	public Component clone(){
		return new RTurningComponent(this);
	}
	
	public void update(int delta, List<Entity> entities) {
		Entity.restrictLength(turn, turnAbility);
		super.update(delta, entities);
		/*FacingComponent fc = (FacingComponent)owner.getComponent("facing");
		Vector3f.add(fc.facing, turn, fc.facing);
		Entity.restrictLength(fc.facing, 1);*/
	}
	public Component getStepped(int delta, List<Entity> entities) {
		RTurningComponent rtc = new RTurningComponent(turn, turnAbility);
		rtc.update(delta, entities);
		return rtc;
	}
}
