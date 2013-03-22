package glTest;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL30.*;
import static org.lwjgl.util.glu.GLU.*; //gluPerspective
import java.util.*;

import org.lwjgl.opengl.*;
import org.lwjgl.*;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

//import tk.sritwinkles.Point;

//RK4 okay.


public class Test3D {

	// basic 3d 

	private boolean mouseEnabled = false;

	private final String TITLE = "Points!";

	// frame independent movement speed using delta
	private long lastFrame;
	
	private double zpos;

	private long getTime() {
		return (Sys.getTime() * 1000) / Sys.getTimerResolution();
	}

	private int getDelta() {
		long currentTime = getTime();
		int delta = (int) (currentTime - lastFrame);
		lastFrame = currentTime;
		return delta;
	}

	// end delta calcs

	private void setUpDisplay() {
		// initialization for Display
		try {
			Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT));
			Display.setTitle(TITLE);
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
			Display.destroy();
			System.exit(0);
		}
	}

	private void setUpOpenGL() {
		// initialization for openGl

		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		// glOrtho(0, WIDTH, 0, HEIGHT, 1, -1);
		gluPerspective((float) 30, (float) WIDTH / HEIGHT, 0.001f, 300);
		glMatrixMode(GL_MODELVIEW);
		glEnable(GL_DEPTH_TEST);
	}

	private void setUpTimer() {
		lastFrame = getTime();
	}

	private void setUpEntities() {
		toUpdate = new ArrayList<Tickable>();
		toRender = new ArrayList<Renderable>();

		addEntities();
	}

	public static final int WIDTH = 640;
	public static final int HEIGHT = 480;

	// all objects that need updating
	public ArrayList<Tickable> toUpdate;
	public ArrayList<Renderable> toRender;

	float speed = 0.0f;
	float quadX = 100;
	float quadY = 100;
	float quadWidth = 100;
	float quadHeight = 100;
	
	public Test3D() {
		setUpDisplay();
		setUpOpenGL();

		setUpEntities();

		setUpTimer();

		while (!Display.isCloseRequested()) {
			// loop
			
			
			tick();
			input();
			render();
			zpos += speed;
			// System.out.println(getDelta());

			Display.update();
			Display.sync(60);
		}

		Display.destroy();
		System.exit(0);
	}

	private void addEntities() {
		// SampleDeltaMover box = new SampleDeltaMover(0, 0, 0, 0);
		Random random = new Random();
		for (int i = 0; i < 10000; i++) {
			Point p = new Point((random.nextFloat() - 0.5f)*200f,
					(random.nextFloat() - 0.5f)*200f, random.nextInt(10000) - 10000);
			toUpdate.add(p);
			toRender.add(p);
		}
	}

	private void tick() {
		for (Tickable o : toUpdate) {
			o.step(getDelta());
		}
		interactions();
	}

	private void interactions() { // how objects react to each other

	}

	private void render() {
		// draw
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		
		glMatrixMode(GL_PROJECTION);
	    gluPerspective((float) 30, (float) WIDTH / HEIGHT, 0.001f, 300);
		glMatrixMode(GL_MODELVIEW);
		
		glLoadIdentity();
		glTranslated(0, 0, zpos);
		
		for (Renderable o : toRender) {
			o.render();
		}
		
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		////glOrtho(0, WIDTH, 0, HEIGHT, -1, 1);
		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();
		
		
		float xMin = quadX/WIDTH -1.f;
		float yMin = quadY/HEIGHT - 1.f;
		float xMax = (quadX + quadWidth)/WIDTH - 1.f;
		float yMax = (quadY + quadHeight)/HEIGHT - 1.f;
		
		//glDisable(GL_DEPTH_TEST);
		glBegin(GL_QUADS);
			glVertex2f(xMin, yMin);
			glVertex2f(xMin, yMax);
			glVertex2f(xMax, yMax);
			glVertex2f(xMax, yMin);
		glEnd();
		
		
		int error = glGetError();
		if (error != GL_NO_ERROR) {
			System.out.println(gluGetString(error));
		}
	}

	private void input() {
		// check for input
		// Sample mouse and key usage

		if (mouseEnabled) {
			int mouseX = Mouse.getX();// - WIDTH / 2;
			int mouseY = Mouse.getY();// - HEIGHT / 2;
			if (Mouse.isButtonDown(0)) {
				quadX = 2*mouseX - quadWidth/2;
				quadY = 2*mouseY - quadHeight/2;
			}
			if (Mouse.isButtonDown(1)) {

				// System.out.println(mouseX + ", " + mouseY);
			}
		}
		
		if (Keyboard.isKeyDown(Keyboard.KEY_E)) {
			mouseEnabled = true;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
			mouseEnabled = false;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
			//mouseEnabled = true;
			//zpos = 0;
			speed = 0;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_R)) {
			//mouseEnabled = false;
			//glLoadIdentity();
			zpos = 0;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
			Display.destroy();
			System.exit(0);
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_UP)) {
			speed += 0.01f;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_DOWN)) {
			speed -= 0.01f;
		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Test3D();

	}

}

class Point implements Tickable, Renderable {
	public Point(float x, float y, float z) {
		super();
		this.x = x;
		this.y = y;
		this.z = z;
	}

	final float x;
	final float y;
	final float z;

	@Override
	public void render() {
		//glPointSize(10);
		//glBegin(GL_3D);
		glBegin(GL_POINTS);
		glVertex3f(x, y, z);
		glEnd();
	}

	@Override
	public void step(int delta) {
		//donothin

	}

}

/*
 * class SampleDeltaMover implements Tickable, Renderable { private double x =
 * 0; private double y = 0; private double dx; private double dy; private static
 * final double BOX_SIZE = Test3D.WIDTH / 20;
 * 
 * public SampleDeltaMover(double x, double y, double dx, double dy) {
 * this.setX(x); this.setY(y); this.setDX(dx); this.setDY(dy); }
 * 
 * public void step(int delta) { x += delta * dx; y += delta * dy; }
 * 
 * @Override public void render() { glRectd(x, y, x + BOX_SIZE, y + BOX_SIZE);
 * //System.out.println(BOX_SIZE); }
 * 
 * public double getX() { return x; }
 * 
 * public void setX(double x) { this.x = x; }
 * 
 * public double getY() { return y; }
 * 
 * public void setY(double y) { this.y = y; }
 * 
 * public double getDX() { return dx; }
 * 
 * public void setDX(double dx) { this.dx = dx; }
 * 
 * public double getDY() { return dy; }
 * 
 * public void setDY(double dy) { this.dy = dy; } }
 */
