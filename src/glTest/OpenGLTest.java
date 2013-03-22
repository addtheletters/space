package glTest;

import static org.lwjgl.opengl.GL11.*;

import java.util.ArrayList;

import org.lwjgl.opengl.*;
import org.lwjgl.*;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

public class OpenGLTest {

	// basic 2d blank

	private boolean mouseEnabled = true;

	private final String TITLE = "HEYYYY";

	// frame independent movement speed using delta
	private long lastFrame;

	private long getTime() {
		return (Sys.getTime() * 1000) / Sys.getTimerResolution();
	}

	private int getDelta() {
		long currentTime = getTime();
		int delta = (int) (currentTime - lastFrame);
		lastFrame = currentTime;
		return delta;
	}

	private void setUpDisplay() {
		// initialization for Display
		try {
			Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT));
			Display.setTitle(TITLE);
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
	}

	private void setUpOpenGL() {
		// initialization for openGl

		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		 glOrtho(0, WIDTH, 0, HEIGHT, 1, -1); 
		// 2d, no perspective, center at 0,0
		//glOrtho(WIDTH/2.0, -WIDTH / 2.0, HEIGHT/2.0, -HEIGHT/2.0, 1, -1);
		 //how the heck does it work? I HAVE NO IDEA
		glMatrixMode(GL_MODELVIEW);
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

	public OpenGLTest() {
		setUpDisplay();
		setUpOpenGL();

		setUpEntities();

		setUpTimer();

		while (!Display.isCloseRequested()) {
			// loop

			tick();
			input();
			render();
			// System.out.println(getDelta());

			// glLoadIdentity();
			Display.update();
			Display.sync(60);
		}

		Display.destroy();
		System.exit(0);
	}

	private void addEntities() {
		SampleDeltaMover box = new SampleDeltaMover(0, 0, 0, 0);
		toUpdate.add(box);
		toRender.add(box);
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
		glClear(GL_COLOR_BUFFER_BIT);// | GL_DEPTH_BUFFER_BIT);
		//glLoadIdentity();
		for (Renderable o : toRender) {
			o.render();
		}
		// glLoadIdentity();

	}

	private void input() {
		// check for input
		// Sample mouse and key usage
		// mouse x and y is determined with 0,0 at the bottom left, not whatever
		// glOrtho says
		SampleDeltaMover sdm = (SampleDeltaMover) toUpdate.get(0);

		if (mouseEnabled) {
			int mouseX = Mouse.getX();// - WIDTH / 2;
			int mouseY = Mouse.getY();// - HEIGHT / 2;
			if (Mouse.isButtonDown(0)) {
				sdm.setDX(0.05 * ((double) mouseX - sdm.getX()));
				sdm.setDY(0.05 * ((double) mouseY - sdm.getY()));
				//System.out.println(mouseX + ", " + mouseY);
			}
			if (Mouse.isButtonDown(1)) {
				sdm.setX(mouseX);
				sdm.setY(mouseY);
				sdm.setDX(0);
				sdm.setDY(0);
				//System.out.println(mouseX + ", " + mouseY);
			}
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_E)) {
			mouseEnabled = true;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
			mouseEnabled = false;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
			Display.destroy();
			System.exit(0);
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_UP)) {
			sdm.setDY(sdm.getDY() + 0.01);
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_DOWN)) {
			sdm.setDY(sdm.getDY() - 0.01);
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) {
			sdm.setDX(sdm.getDX() + 0.01);
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_LEFT)) {
			sdm.setDX(sdm.getDX() - 0.01);
		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new OpenGLTest();

	}

}

class SampleDeltaMover implements Tickable, Renderable {
	private double x = 0;
	private double y = 0;
	private double dx;
	private double dy;
	private static final double BOX_SIZE = OpenGLTest.WIDTH / 20;

	public SampleDeltaMover(double x, double y, double dx, double dy) {
		this.setX(x);
		this.setY(y);
		this.setDX(dx);
		this.setDY(dy);
	}

	public void step(int delta) {
		x += delta * dx;
		y += delta * dy;
	}

	@Override
	public void render() {
		glRectd(x, y, x + BOX_SIZE, y + BOX_SIZE);
		//System.out.println(BOX_SIZE);
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getDX() {
		return dx;
	}

	public void setDX(double dx) {
		this.dx = dx;
	}

	public double getDY() {
		return dy;
	}

	public void setDY(double dy) {
		this.dy = dy;
	}
}
