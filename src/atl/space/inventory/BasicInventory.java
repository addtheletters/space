package atl.space.inventory;

import java.util.ArrayList;
import java.util.List;

import static atl.space.inventory.InventoryException.InventoryOperation.*;
import atl.space.inventory.InventoryException.InventoryOperation;
import atl.space.inventory.items.Item;

public class BasicInventory implements Inventory{
	//unlimited size inventory
	private static final boolean DEBUG = true;
	
	List<Item> items;
	
	public BasicInventory(){
		items = new ArrayList<Item>();
	}
	
	public BasicInventory(Inventory i){
		items = i.getItems();
	}
	
	@Override
	public boolean isFull() {
		return false;
	}

	@Override
	public void setCapacity(double capacity) {
		//does nothing, unlimited inventory size
		if(DEBUG) System.err.println("DEBUG: Tried to set size of a basic unlimited inventory");
	}

	@Override
	public double getCapacity() {
		return Double.MAX_VALUE;
	}

	@Override
	public double getCapacityRemaining() {
		return getCapacity() - getCapacityFilled();
	}

	@Override
	public boolean canFit(double size) {
		return getCapacityRemaining() >= size;
	}

	@Override
	public double getCapacityFilled() {
		double tempsum = 0;
		for(Item i : items){
			tempsum += i.getSize();
		}
		return tempsum;
	}

	@Override
	public void addItem(Item item) throws InventoryException {
		if(!items.add(item)) throw new InventoryException(ADD, item.getID(), this);
	}

	@Override
	public void removeItem(Item item) throws InventoryException {
		if(!items.remove(item)) throw new InventoryException(REMOVE, item.getID(), this);
	}

	@Override
	public List<Item> getItems() {
		return items;
	}

	@Override
	public boolean containsItem(Item item) {
		return items.contains(item);
	}

	public boolean containsItem(String itemID) throws InventoryException{
		return !(getItem(itemID) == null);
	}

	@Override
	public Item getItem(String itemID) throws InventoryException{
		Item tmp = getFromList(itemID, items);
		if(tmp == null){
			throw new InventoryException(InventoryOperation.GET, itemID, this);
		}
		return tmp;
	}
	
	public static Item getFromList(String itemID, List<Item> list){
		Item tmp = null;
		for(Item i : list){
			if(itemID == i.getID()) tmp = i;
			break;
		}
		return tmp;
	}

}
