package atl.space.inventory;

import java.util.Collection;

public interface Inventory {
	public boolean isFull();
	public int getCapacity();
	public int getSpaceRemaining();
	public boolean canFit(int size);
	public int getSpaceFilled();
	public void addItem(Item item);
	public Collection<Item> getItems();
	public boolean containsItem(Item item);
	public boolean getItem(String itemID);
}
