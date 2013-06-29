package atl.space.rpg;

public abstract class Event {
	public abstract void trigger();
	public abstract Event getConsequence();
}
