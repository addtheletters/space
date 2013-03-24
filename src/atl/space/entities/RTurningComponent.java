package atl.space.entities;

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
	public void update(int delta) {
		Entity.restrictLength(turn, turnAbility);
		FacingComponent fc = (FacingComponent)owner.getComponent("facing");
		Vector3f.add(fc.facing, turn, fc.facing);
		Entity.restrictLength(fc.facing, 1);
	}
	public Component getStepped(int delta) {
		RTurningComponent rtc = new RTurningComponent(turn, turnAbility);
		rtc.update(delta);
		return rtc;
	}
}
