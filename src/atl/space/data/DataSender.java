package atl.space.data;

public interface DataSender{
	
	public void enableData();
	public void disableData();
	public boolean dataEnabled();
	
	public Data getData();
	
}
