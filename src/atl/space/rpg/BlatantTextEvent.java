package atl.space.rpg;

public abstract class BlatantTextEvent implements Event {
	
	protected abstract String getMessage();
	
	@Override
	public void launch() {
		System.out.println(getMessage());
	}

}
