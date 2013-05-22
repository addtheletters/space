package atl.space.components.mass;


import atl.space.components.Component;

public abstract class AbstractMassiveComponent extends Component implements Massive{
	
	//This is more of a sample than something you would actually extend. I may end up deleting this later.
	
	public AbstractMassiveComponent(){
		super("massgiver");
	}
	
	public abstract double getMass(); //not even an implementation! But I feel like having it!
	

}
