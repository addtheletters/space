package tk.sritwinkles;

import static org.lwjgl.opengl.GL11.*;
//import org.lwjgl.opengl.*;
//import org.lwjgl.*;

//Can represent a point in 3d space
//Can also represent a vector starting at the origin(0,0,0)

public class Point implements glTest.Renderable, glTest.Tickable{

	protected double xPos;
	protected double yPos;
	protected double zPos;

	public Point() {
		xPos = 0;
		yPos = 0;
		zPos = 0;
	}

	public Point(double x, double y, double z) {
		xPos = x;
		yPos = y;
		zPos = z;
	}

	public Point(Point p) {
		xPos = p.getXPos();
		yPos = p.getYPos();
		zPos = p.getZPos();
	}

	public double getXPos() {
		return xPos;
	}

	public double getYPos() {
		return yPos;
	}

	public double getZPos() {
		return zPos;
	}

	public double getDistanceTo(Point p) {
		return Math.sqrt(Math.pow((xPos - p.getXPos()), 2)
				+ Math.pow((yPos - p.getYPos()), 2)
				+ Math.pow((zPos - p.getZPos()), 2));
	}

	public double getMagnitude() { // assumes Point is used as a vector
									// originating from (0,0,0)
		return getDistanceTo(new Point());
	}

	public Point getAdditionTo(Point p) {
		return new Point(xPos + p.getXPos(), yPos + p.getYPos(), zPos
				+ p.getZPos());
	}

	public void add(Point p) {
		xPos += p.getXPos();
		yPos += p.getYPos();
		zPos += p.getZPos();
	}

	public Point toUnitVector() {
		if (xPos == 0 && yPos == 0 && zPos == 0)
			return new Point(0, 0, 0);
		return new Point(getXPos() / getMagnitude(),
				getYPos() / getMagnitude(), getZPos() / getMagnitude());
	}

	public void setToUnitVector() {
		if (xPos == 0 && yPos == 0 && zPos == 0)
			return;
		xPos = getXPos() / getMagnitude();
		yPos = getYPos() / getMagnitude();
		zPos = getZPos() / getMagnitude();
	}

	public Point multiplyMagnitude(double factor) {
		return new Point(getXPos() * factor, getYPos() * factor, getZPos()
				* factor);
	}

	public void setXPos(double n) {
		xPos = n;
	}

	public void setYPos(double n) {
		yPos = n;
	}

	public void setZPos(double n) {
		zPos = n;
	}

	public Point normalizeTo(Point p) {
		return new Point(xPos - p.getXPos(), yPos - p.getYPos(), zPos
				- p.getZPos());
	}

	public String toString() {
		return "(" + (float) xPos + ", " + (float) yPos + ", " + (float) zPos
				+ ")";
	}
	
	public void render(){
		glPointSize(10);
		glBegin(GL_3D);
		glVertex3f((float)xPos, (float)yPos, (float) zPos);
		glEnd();
	}

	@Override
	public void step(int delta) {
		// TODO Auto-generated method stub
		//STIL DOES NOTHING QHJGFQQEHJDG
	}
}