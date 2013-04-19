package atl.space.components.collision;

import java.util.List;

import atl.space.components.Component;
import atl.space.entities.Entity;

public class RectObstructionComponent extends ObstructionComponent {
	public float xSize;
	public float ySize;
	public float zSize;
	//extensions will modify these values through stepping so that this class's hasCollided does not need modifying
	

	public RectObstructionComponent() {
		id = "obstruction";
		xSize = 0;
		ySize = 0;
		zSize = 0;
	}
	
	public RectObstructionComponent(RectObstructionComponent roc){
		this.id = roc.id;
		xSize = roc.xSize;
		ySize = roc.ySize;
		zSize = roc.zSize;
	}
	
	public Component clone(){
		return new RectObstructionComponent(this);
	}
	
	@Override
	public boolean hasCollided(Entity e, int delta, List<Entity> entities) {
		Coord minX, maxX, minY, maxY, minZ, maxZ;
		Entity steppedThis = owner.getStepped(delta, entities);
		Entity steppedOther = e.getStepped(delta, entities);
		RectObstructionComponent otherComp = (RectObstructionComponent) steppedOther
				.getComponent("obstruction");
		RectObstructionComponent thisComp = (RectObstructionComponent) steppedThis
				.getComponent("obstruction");

		if (owner.position.x >= e.position.x) {
			minX = new Coord(steppedOther.position.x, otherComp.xSize);
			maxX = new Coord(steppedThis.position.x, thisComp.xSize);
		} else {
			maxX = new Coord(steppedOther.position.x, otherComp.xSize);
			minX = new Coord(steppedThis.position.x, thisComp.xSize);
		}
		if (owner.position.y >= e.position.y) {
			minY = new Coord(steppedOther.position.y, otherComp.ySize);
			maxY = new Coord(steppedThis.position.y, thisComp.ySize);
		} else {
			maxY = new Coord(steppedOther.position.y, otherComp.ySize);
			minY = new Coord(steppedThis.position.y, thisComp.ySize);
		}
		if (owner.position.z >= e.position.z) {
			minZ = new Coord(steppedOther.position.z, otherComp.zSize);
			maxZ = new Coord(steppedThis.position.z, thisComp.zSize);
		} else {
			maxZ = new Coord(steppedOther.position.z, otherComp.zSize);
			minZ = new Coord(steppedThis.position.z, thisComp.zSize);
		}
		if ((minX.value + minX.obsSize / 2 >= maxX.value - maxX.obsSize / 2)
				&& (minY.value + minY.obsSize / 2 >= maxY.value - maxY.obsSize
						/ 2)
				&& (minZ.value + minZ.obsSize / 2 >= maxZ.value - maxZ.obsSize
						/ 2)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean isTouching(Entity e) {
		// TODO Make this work XD
		return false;
	}

	@Override
	public void update(int delta, List<Entity> entities) {
		// do nothing?
	}

	@Override
	public Component getStepped(int delta, List<Entity> entities) {
		RectObstructionComponent roc = new RectObstructionComponent();
		roc.update(delta, entities);
		return roc;
	}

	protected class Coord {
		public double value;
		public double obsSize;

		public Coord(double v, double s) {
			value = v;
			obsSize = s;
		}
	}

}
