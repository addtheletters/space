package tk.sritwinkles;

//Represents an object with size, moving  

public class Obstruction extends Entity {
	protected double size;
	private final int MAXSTEPS = 100; // for calculating collisions, not working
										// currently

	public Obstruction() {
		super();
		size = 1;
	}

	public Obstruction(Obstruction o) {
		super(o);
		size = o.getSize();
	}

	public Obstruction(Point p, double s) {
		super(p, 0, 0, 0);
		size = s;
	}

	public Obstruction(Point p, Point sv, double s) { // sv represents a vector
														// in xyz form, from the
														// origin, representing
														// speed
		super(p, sv);
		size = s;
	}

	public Obstruction(Entity e, double s) {
		super(e);
		size = s;
	}

	public Obstruction(double x, double y, double z) {
		super(x, y, z);
		size = 1;
	}

	public Obstruction(double x, double y, double z, double xs, double ys,
			double zs) {
		super(x, y, z, xs, ys, zs);
		size = 1;
	}

	public Obstruction(double s) {
		super();
		size = s;
	}

	public Obstruction(double x, double y, double z, double s) {
		super(x, y, z);
		size = s;
	}

	public Obstruction(double x, double y, double z, double xs, double ys,
			double zs, double s) {
		super(x, y, z, xs, ys, zs);
		size = s;
	}

	public double getSize() {
		return size;
	}

	public void setSize(double s) {
		size = s;
	}

	// /VECTOR ALGEBRA N STUFF YEAAAAAAA
	// Current implementation fails with fast-moving objects!!!!
	// Implementation of actual working methods can wait until I figure out if
	// they actually need these methods

	public boolean willTouch(Obstruction o) { // if they will ever touch
		/*
		 * if( getPathMinDistance(o) <= distanceBetweenWhenTouching(o)) return
		 * true; else return false;
		 */
		// need to implement to account for time of passing
		Obstruction temp = o;
		Obstruction thisTemp = this;
		for (int i = 0; i < MAXSTEPS; i++) {
			temp = temp.getStepped();
			thisTemp = thisTemp.getStepped();
			if (thisTemp.isTouching(temp)) {
				return true;
			}
		}
		return false;

	}

	public boolean willTouch(Obstruction o, int steps) { // they will touch in
															// steps steps of
															// fewer, steps must
															// be positive
		Obstruction temp = o;
		Obstruction thisTemp = this;
		for (int i = 0; i < steps; i++) {
			temp = temp.getStepped();
			thisTemp = thisTemp.getStepped();
			if (thisTemp.isTouching(temp)) {
				return true;
			}
		}
		return false;
		// May want to use a cleaner implementation using vector math like
		// willTouch(Obstruction)
	}

	public int timeToTouch(Obstruction o) { // steps until touch, returns -1 if
											// will never touch
		if (!willTouch(o)) {
			return -1;
		}
		Obstruction temp = o;
		Obstruction thisTemp = this;

		for (int i = 0; i < MAXSTEPS; i++) {
			temp = temp.getStepped();
			thisTemp = thisTemp.getStepped();
			if (thisTemp.isTouching(temp)) {
				return i + 1;
			}
		}
		return -1;
	}

	// end of borked methods

	// 1s are less than 2s
	// coords (xa, ya, za) (xb, yb, zb)
	// x1 = min(xa, xb)
	// x2 = max(xa, xb)
	// y1 = min(ya, yb)
	// ...
	// if stepped(x1) + size/2 > stepped(x2) - size/2
	// stepped(y1) + size/2 > stepped(y2) - size/2
	// stepped(z1) + size/2 > stepped(z2) - size/2
	// then collision in this frame

	public boolean collide(Obstruction o) { // returns true if will collide with
											// o next step!
		Coord minX, maxX, minY, maxY, minZ, maxZ;
		if (xPos >= o.getXPos()) {
			minX = new Coord(o.getStepped().getXPos(), o.getSize());
			maxX = new Coord(getStepped().xPos, size);
		} else {
			maxX = new Coord(o.getStepped().getXPos(), o.getSize());
			minX = new Coord(getStepped().xPos, size);
		}
		if (yPos >= o.getYPos()) {
			minY = new Coord(o.getStepped().getYPos(), o.getSize());
			maxY = new Coord(getStepped().yPos, size);
		} else {
			maxY = new Coord(o.getStepped().getYPos(), o.getSize());
			minY = new Coord(getStepped().yPos, size);
		}
		if (zPos >= o.getZPos()) {
			minZ = new Coord(o.getStepped().getZPos(), o.getSize());
			maxZ = new Coord(getStepped().zPos, size);
		} else {
			maxZ = new Coord(o.getStepped().getZPos(), o.getSize());
			minZ = new Coord(getStepped().zPos, size);
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

	public boolean isTouching(Obstruction o) {
		if (getDistanceTo(o) <= distanceBetweenWhenTouching(o))
			return true;
		else
			return false;
	}

	public double distanceBetweenWhenTouching(Obstruction o) {
		return (size / 2) + (o.getSize() / 2);
	}

	public Obstruction getStepped() {
		return new Obstruction(new Point(xPos + speed.getXPos(), yPos
				+ speed.getYPos(), zPos + speed.getZPos()), speed, size);
	}

	public void hit(Obstruction o) {
		// do stuff???

	}

	public String toString() {
		return super.toString() + "Size: " + (float) size + "\n";
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