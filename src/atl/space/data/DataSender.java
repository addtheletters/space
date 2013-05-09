package atl.space.data;

public interface DataSender{
	
	public void enable();
	public void disable();
	public boolean enabled();
	
	public Data getData();
	
}
