package atl.space.components;

public interface Enableable extends Triggerable {
	public boolean isEnabled();
	public void setEnabled(boolean arg);
	public void enable();
	public void disable();
}
