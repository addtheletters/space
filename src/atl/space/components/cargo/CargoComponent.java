package atl.space.components.cargo;

import java.util.ArrayList;
import java.util.List;

import atl.space.components.Component;
import atl.space.components.mass.Massive;
import atl.space.entities.Entity;
import atl.space.inventory.BasicInventory;
import atl.space.inventory.Inventory;
import atl.space.inventory.InventoryException;
import atl.space.inventory.InventoryException.InventoryOperation;
import atl.space.inventory.items.Item;

public class CargoComponent extends Component implements Massive, Inventory{
	
	public List<Item> cargo;
	private double capacity;
	
	private static final double DEFAULT_CAPACITY = 100;
	
	public CargoComponent(){
		this("cargo");
	}
	
	public CargoComponent(String id) {
		super(id);
		cargo = new ArrayList<Item>();
		setCapacity(DEFAULT_CAPACITY);
	}
	public CargoComponent(List<Item> cargo, double capacity){
		super("cargo");
		this.cargo = cargo;
		this.setCapacity(capacity);
	}
	
	public CargoComponent(CargoComponent cc){
		super(cc.getId());
		this.cargo = cc.getItems();
		this.setCapacity(cc.getCapacity());
	}
	
	
	@Override
	public Component clone() {
		return new CargoComponent(this);
	}

	@Override
	public void update(int delta, List<Entity> entities) {
		//do nothing?
	}
	
	
	@Override
	public double getMass() {
		return getCapacityFilled();
	}

	@Override
	public boolean isFull() {
		return getCapacityRemaining() == 0;
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
		double totalSize = 0;
		for(Item i : cargo){
			totalSize += i.getSize();
		}
		return totalSize;
	}

	@Override
	public void addItem(Item item) throws InventoryException {
		if(!cargo.add(item)){
			throw new InventoryException(InventoryOperation.ADD, item.getID(), this);
		}
	}

	@Override
	public void removeItem(Item item) throws InventoryException{
		if(!cargo.remove(item)){
			throw new InventoryException(InventoryOperation.REMOVE, item.getID(), this);
		}
	}

	@Override
	public List<Item> getItems() {
		return cargo;
	}

	@Override
	public boolean containsItem(Item item) {
		return cargo.contains(item);
	}
	
	public boolean containsItem(String itemID) throws InventoryException{
		return !(getItem(itemID) == null);
	}

	@Override
	public Item getItem(String itemID) throws InventoryException{
		Item tmp = BasicInventory.getFromList(itemID, cargo);
		if(tmp == null){
			throw new InventoryException(InventoryOperation.GET, itemID, this);
		}
		return tmp;
	}
	
	

	public double getCapacity() {
		return capacity;
	}

	public void setCapacity(double capacity) {
		this.capacity = capacity;
	}

}
