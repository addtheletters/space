package atl.space.rpg;

import java.util.List;

public abstract class Fork extends Event {
	public List<Option> options;
	public abstract Event getConsequence(List<Option> choicesMade);
}
