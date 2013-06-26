package atl.space.inventory;

@SuppressWarnings("serial")
public class InventoryException extends Exception {
	private InventoryOperation op;
	private Item item;
	private Inventory inventory;
	
	public InventoryException(InventoryOperation io, String itemID, Inventory inventory){
		super("Inventory: Failed to perform [" + io + "] on inventory " + inventory + " with item of ID " + itemID);
		setOp(io);
		this.setItem(item);
		this.setInventory(inventory);
	}
	
	public InventoryOperation getOp() {
		return op;
	}

	public void setOp(InventoryOperation op) {
		this.op = op;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public Inventory getInventory() {
		return inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}

	public enum InventoryOperation{
		ADD, REMOVE, GET;
	}
}
