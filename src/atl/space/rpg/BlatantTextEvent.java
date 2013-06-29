package atl.space.rpg;

public abstract class BlatantTextEvent implements Event {
	
	protected abstract String getMessage();
	protected void launchNext(){
		//do nothing until extended
	}
	
	@Override
	public void launch() {
		System.out.println(getMessage());
		launchNext();
	}

}
