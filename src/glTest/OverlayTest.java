package glTest;

import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_TEST;
import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_NO_ERROR;
import static org.lwjgl.opengl.GL11.GL_ONE_MINUS_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.GL_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBlendFunc;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glGetError;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.util.glu.GLU.gluGetString;
import static org.lwjgl.util.glu.GLU.gluPerspective;

import java.util.List;

import me.zwad3.space.options.R;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.util.Color;
import org.lwjgl.util.vector.Vector3f;

import utility.Camera;
import utility.FlyCamera;
import atl.space.components.data.DataBank;
import atl.space.components.data.sensor.OmniscientRangedEntitySensor;
import atl.space.data.Data;
import atl.space.entities.Entity;
import atl.space.entities.EntityBuilder;
import atl.space.world.Environment;


//RK4 okay.

//Arrow keys or wasd for X and Y movement
//Space to rise
//Leftshift to fall
//Enter/Return to reset camera position to "center"
//Click to lock mouse and look
//Rightclick to unlock mouse

/*Cyan-red indicates the direction it's facing
 Blue-green represents a turning vector
 Magenta-green is the acceleration vector
 Acceleration is based off of primary thrust in the direction it's facing added to the secondary vector*/

public class OverlayTest {

	// Static Constants (Or at least it should be imo)

	private static final boolean DEBUG = true;
	private final String TITLE = "Sensors!";

	public static final int WIDTH = R.WIDTH;
	public static final int HEIGHT = R.HEIGHT;

	// List of dynamic variables used to calculate where the camera is
	private boolean mouseEnabled = true;

	// frame independent movement speed using delta
	private long lastFrame;

	private Camera camera;

	// all objects that need updating
	// public ArrayList<Entity> entities;
	public Environment entities;

	Entity protag;

	// Data representing the field

	final float STAR_FIELD_SIZE = 5000;
	final int NUM_STARS = 1000;
	final int NUM_TRAILERS = 10;
	final float TRAILER_SPEED = 0;
	final float TRAIL_FADE = 0.005f;
	final int TRAIL_LENGTH = 200;
	final int NUM_FACERS = 0;
	final int NUM_ACCELERATORS = 0;
	final int NUM_DUMB_AUTO = 0;
	final int NUM_SMART_AUTO = 0;
	final float TURNLIM = 0.01f; // how fast the turning is, tho it's still
									// randomized
	final float ACCELERATION = 0.02f;

	final int NUM_GRAVPULLERS = 5;
	final int NUM_SIMPLEGRAVPULLABLES = 20;

	final float[] maxAccel = new float[] { ACCELERATION, ACCELERATION / 2,
			(float) (ACCELERATION / 1.3) };
	// primary, reverse, secondary

	/*boolean smartAccelFwd = false;
	boolean smartTurn = false;
	boolean smartAccelBack = false;
	boolean smartAccelSec = false;
	boolean smartStopAccel = false;
	boolean smartStop = false;
	boolean launchMissile = false;
	*/
	
	//boolean moveProtag = false;
	
	final float FOV = 45f;
	final public static float ASPECT_RATIO = (float) WIDTH / HEIGHT;
	final float CLOSE_RENDER_LIM = 0.1f;
	final float FAR_RENDER_LIM = 10000;

	// for vertex buffer objects

	final int VERTEX_DIM = 3; // 3 dimensions
	final int COLOR_DIM = 3; // no alpha, or it would be 4

	final float camAccel = 50f;
	float quadX = 100;
	float quadY = 100;
	float quadWidth = 100;
	float quadHeight = 100;

	/**
	 * Returns the current time in ms
	 * 
	 * @return system time in milliseconds
	 * @category helper method
	 */
	private long getTime() {
		return (Sys.getTime() * 1000) / Sys.getTimerResolution();
	}

	/**
	 * Calculates how long since the last frame draw
	 * 
	 * @return time since last frame
	 * @category helper method
	 */

	private int getDelta() {
		long currentTime = getTime();
		int delta = (int) (currentTime - lastFrame);
		lastFrame = currentTime;
		return delta;
	}

	/**
	 * Creates the display for the viewing box
	 * 
	 * @category setup
	 */
	private void setUpDisplay() {
		// initialization for Display
		try {
			Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT));
			Display.setTitle(TITLE);
			Display.setVSyncEnabled(true);
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
			Display.destroy();
			System.exit(0);
		}
	}

	/**
	 * Sets up a blank OpenGL window
	 * 
	 * @category setup
	 * 
	 */
	private void setUpOpenGL() {
		// initialization for openGl

		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		// glOrtho(0, WIDTH, 0, HEIGHT, 1, -1);
		gluPerspective(FOV, ASPECT_RATIO, CLOSE_RENDER_LIM, FAR_RENDER_LIM);
		glMatrixMode(GL_MODELVIEW);
		glEnable(GL_DEPTH_TEST);
		glEnable(GL_BLEND);
		glEnable(GL_TEXTURE_2D);
		glClearColor(0f, 0f, 0f, 0f);
		// glEnable(GL_DONT_CARE);
		// System.out.println(GL_DONT_CARE);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
	}

	private void setUpCamera() {
		camera = new FlyCamera.Builder().setAspectRatio(ASPECT_RATIO)
				.setRotation(0f, 0f, 0f).setPosition(0f, 0f, 0f)
				.setFieldOfView(FOV).build();
		camera.applyOptimalStates();
		camera.applyPerspectiveMatrix();
		// setUpProjectionMatrices();
	}

	/**
	 * Initializes the timer
	 * 
	 * @category setup
	 */
	private void setUpTimer() {
		lastFrame = getTime();
	}

	/**
	 * Creates an empty list of entities and fills it
	 * 
	 * @category setup
	 */
	private void setUpEntities() {
		entities = new Environment();

		addEntities();
	}

	/**
	 * Calls all the @setup methods and enters the main loop
	 * 
	 * Uses time-independent rendering and calculates the change in location as
	 * a function of of the change in time and the acceleration/speed
	 * 
	 */

	public OverlayTest() {
		setUpDisplay();
		setUpOpenGL();

		setUpCamera();

		setUpEntities();
		setUpTimer();

		while (!Display.isCloseRequested()) {
			// loop

			double delta = getDelta();
			// System.out.println(delta);
			// tick(delta);
			input();
			tick(delta);
			render();
			// zpos += zspeed * delta;
			// xpos += xspeed * delta;
			// ypos += yspeed * delta;
			// System.out.println(getDelta());

			Display.update();
			Display.sync(60);

			//smartStopAccel = false;
			//smartStop = false;
			// smartTurn = false;
		}

		quit();
	}

	/**
	 * Yeah...
	 * 
	 * @Destroys THE WORLD!!!!!
	 * 
	 */
	private void quit() {
		if (DEBUG)
			System.out.println("Stopping...");
		Display.destroy();
		System.exit(0);
	}

	/**
	 * Adds starting entities to the environment.
	 */
	private void addEntities() {
		for (int i = 0; i < NUM_STARS; i++) {
			addPoint(numInFeild(), numInFeild(), numInFeild());
		}
		for (int i = 0; i < NUM_GRAVPULLERS; i++) {
			addGravityPuller(numInFeild(), numInFeild(), numInFeild(),
					genPullForce());
		}
		for (int i = 0; i < NUM_SIMPLEGRAVPULLABLES; i++) {
			addSimpleGravityPullable(numInFeild(), numInFeild(), numInFeild(),
					genRandMass());
		}
		addProtagonist();
	}

	private double genPullForce() {
		return 300;
	}

	private double genRandMass() {
		return 30;
	}

	/**
	 * Creates a random number that fits w/i STAR_FIELD_SIZE
	 * 
	 * @return randum number w/i STAR_FIELD_SIZE
	 */
	private float numInFeild() {
		return (float) (Math.random() * STAR_FIELD_SIZE) - STAR_FIELD_SIZE / 2;
	}

	private Vector3f randTurn() {
		return new Vector3f((float) (Math.random() - .5) * TURNLIM,
				(float) (Math.random() - .5) * TURNLIM,
				(float) (Math.random() - .5) * TURNLIM);
	}

	public static Color randomColor(int alpha) {
		// alpha is from 0 to 255
		return new Color((int) (Math.random() * 255),
				(int) (Math.random() * 255), (int) (Math.random() * 255), alpha);
	}

	
	
	private void addProtagonist() {
		protag = EntityBuilder.protagonist(new Vector3f(), randTurn(),
				maxAccel[0], maxAccel[1], maxAccel[2], TURNLIM, camera);
		protag.addComponent(new OmniscientRangedEntitySensor(500)); //TODO make this work
		entities.add(protag);
	}

	private void addPoint(float x, float y, float z) {
		entities.add(EntityBuilder.point(x, y, z));
	}

	
	private void addGravityPuller(float x, float y, float z, double pullForce) {
		Entity temp = EntityBuilder.gravityPuller(new Vector3f(x, y, z),
				pullForce);
		EntityBuilder.addDistanceDisplayTraitTo(temp, camera);
		entities.add(temp);
	}

	private void addSimpleGravityPullable(float x, float y, float z, double mass) {
		Entity temp = EntityBuilder.simpleGravityPullable(
				new Vector3f(x, y, z), mass);
		EntityBuilder.addDistanceDisplayTraitTo(temp, camera);
		entities.add(temp);
	}


	/**
	 * Calculates the new location of each of the entities in `entities`
	 * 
	 * More importantly, it calculates the new location of the camera
	 * 
	 * @param delta
	 */
	private void tick(double delta) {
		entities.updateWorld((int) delta);
		interactions();
	}


	private void interactions() { // how objects react to each other
		
	}

	/**
	 * Updates the canvas according to the new matrix and projection
	 * 
	 * @exciting no
	 * 
	 */

	private void render() {
		// draw
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

		glLoadIdentity();

		// glTranslated(xpos, ypos, zpos);
		camera.applyTranslations();

		glMatrixMode(GL_PROJECTION);
		gluPerspective(FOV, ASPECT_RATIO, CLOSE_RENDER_LIM, FAR_RENDER_LIM);
		glMatrixMode(GL_MODELVIEW);

		/*
		 * for (Entity e : entities) { e.render(); } Old render, switching to
		 * Data Translation Display system:
		 */
		
		DataBank availableDisplayBank = (DataBank)protag.getComponent("databank");
		List<Entity> toDisplay = Data.convertDataToEntities( availableDisplayBank.getData() );
		
		//if(DEBUG) System.out.println("[DEBUG]: gotem");
		for (Entity e: toDisplay){
			e.render();
			//if(DEBUG) System.out.println("[DEBUG] rendered something");
		}
		
		
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();

		// Errors?
		int error = glGetError();
		if (error != GL_NO_ERROR) {
			System.out.println(gluGetString(error));
		}
	}

	/**
	 * Checks the input of both the keyboard and mouse for ceartain actions
	 * 
	 * @recommend replace all the if statements with switch statements
	 */
	private void input() {
		// check for input
		// Sample mouse and key usage
		camera.processKeyboard(16f, camAccel);

		if (mouseEnabled) {
			if (Mouse.isButtonDown(0)) {
				Mouse.setGrabbed(true);
			} else if (Mouse.isButtonDown(1)) {
				Mouse.setGrabbed(false);
			}
			if (Mouse.isGrabbed()) {
				camera.processMouse(0.5f, 85, -85);
			}
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_N)) {
			mouseEnabled = true;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_RETURN)) {
			// return camera to center
			camera.setPosition(0, 0, 0);
			camera.setRotation(0, 0, 0);
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
			quit();
		}
		

	}

	public static void main(String[] args) {
		new OverlayTest();

	}

}
