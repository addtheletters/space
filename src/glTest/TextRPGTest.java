package glTest;

import static org.lwjgl.opengl.GL11.*;

import java.util.ArrayList;

import org.lwjgl.opengl.*;
import org.lwjgl.*;
import org.lwjgl.input.Keyboard;

public class TextRPGTest {
	
	//I found this which may be useful: http://sourceforge.net/projects/nifty-gui/

	private boolean mouseEnabled = true;

	private final String TITLE = "RPG Graphics / Gui test!";

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
		glMatrixMode(GL_MODELVIEW);
	}

	private void setUpTimer() {
		lastFrame = getTime();
	}


	public static final int WIDTH = 640;
	public static final int HEIGHT = 480;

	// all objects that need updating
	public ArrayList<Tickable> toUpdate;
	public ArrayList<Renderable> toRender;

	public TextRPGTest() {
		setUpDisplay();
		setUpOpenGL();

		
		
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
	}

	private void input() {
		// check for input
		// Sample mouse and key usage
		// mouse x and y is determined with 0,0 at the bottom left, not whatever
		// glOrtho says

		if (mouseEnabled) {
			
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

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new OpenGLTest();

	}

}

