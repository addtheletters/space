package atl.space.inventory;

import java.util.Collection;

public interface Inventory {
	public boolean isFull();
	public void setCapacity(double capacity);
	public double getCapacity();
	public double getCapacityRemaining();
	public boolean canFit(double size);
	public double getCapacityFilled();
	public void addItem(Item item) throws InventoryException;
	public void removeItem(Item item) throws InventoryException;
	public Collection<Item> getItems();
	public boolean containsItem(Item item);
	public Item getItem(String itemID) throws InventoryException;
}
