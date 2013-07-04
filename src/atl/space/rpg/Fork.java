package atl.space.rpg;

import java.util.List;

public abstract class Fork implements Event{
	protected List<Option> choices;
	
	public Fork(List<Option> options){
		choices = options;
	}
	
	public void addChoice(Option choice){
		choices.add(choice);
	}
	
	public void removeChoice(int index){
		choices.remove(index);
	}
	
	public void removeChoice(Option choice){
		choices.remove(choice);
	}
	
	public void choose(int index){
		choices.get(index).applyConsequence();
		applyUniversalConsequence();
	}
	
	public void launch(){
		displayUniversalChoiceMessage();
		choose(requestChoice());
		applyUniversalConsequence();
	}
	
	public abstract int requestChoice();
	
	public abstract void displayUniversalChoiceMessage();
	
	public abstract void applyUniversalConsequence();
	
	public void displayText(){
		for(int i = 0; i < choices.size(); i++){
			System.out.println( (i+1) + ": " + choices.get(i).getDescription());
		}
	}
}
