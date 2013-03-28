package glTest;

import atl.space.entities.*;

import static org.lwjgl.opengl.GL11.*;
//import static org.lwjgl.opengl.GL30.*;
//import static org.lwjgl.opengl.GL15.*; //Vertex Buffer Array Rendering n'stuff
import static org.lwjgl.util.glu.GLU.*; //gluPerspective

//import java.nio.FloatBuffer;
import java.util.*;

import org.lwjgl.opengl.*;
import org.lwjgl.util.vector.Vector3f;
//import org.lwjgl.util.Color;
import org.lwjgl.*;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

//import tk.sritwinkles.Point;

//RK4 okay.

//F and V for z-axis movement
//Arrow keys for X and Y movement
//E to enable mouse, D to disable mouse
//With mouse enabled:
//Right click / Hold space to drag XY view
//Right click or Hold Space + Scroll wheel for zoom / z axis movement
//Click / spin scroll wheel to keep same XY movement and shift along Z
//S to stop all camera movement
//R to reset camera position to "center"

public class EntityTest {

	// basic 3d

	private boolean mouseEnabled = true;

	private final String TITLE = "Entities!";

	// frame independent movement speed using delta
	private long lastFrame;
	
	private double zpos;
	private double xpos;
	private double ypos;

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
			Display.setVSyncEnabled(true);
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
		gluPerspective(FOV, ASPECT_RATIO, CLOSE_RENDER_LIM, FAR_RENDER_LIM);
		glMatrixMode(GL_MODELVIEW);
		glEnable(GL_DEPTH_TEST);
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
	}

	private void setUpTimer() {
		lastFrame = getTime();
	}

	private void setUpEntities() {
		entities = new ArrayList<Entity>();

		addEntities();
	}

	public static final int WIDTH = 640;
	public static final int HEIGHT = 480;

	// all objects that need updating
	public ArrayList<Entity> entities;
	// public ArrayList<Renderable> toRender;
	// Made obsolete by new VBO render code.

	final float STAR_FEILD_SIZE = 5000;
	final float NUM_STARS = 1000;
	final float NUM_TRAILERS = 40;
	final float TRAILER_SPEED = 6;
	final float TRAIL_FADE = 0.005f;
	final int TRAIL_LENGTH = 200;
	final float NUM_FACERS = 0;

	final float FOV = 45f;
	final float ASPECT_RATIO = (float) WIDTH / HEIGHT;
	final float CLOSE_RENDER_LIM = 0.1f;
	final float FAR_RENDER_LIM = 10000;

	// for vertex buffer objects

	final int VERTEX_DIM = 3; // 3 dimensions
	final int COLOR_DIM = 3; // no alpha, or it would be 4

	final float camAccel = 0.07f;
	float zspeed = 0.0f;
	float xspeed = 0.0f;
	float yspeed = 0.0f;
	float quadX = 100;
	float quadY = 100;
	float quadWidth = 100;
	float quadHeight = 100;

	public EntityTest() {
		setUpDisplay();
		setUpOpenGL();

		setUpEntities();
		setUpTimer();

		while (!Display.isCloseRequested()) {
			// loop
			double delta = getDelta();
			// System.out.println(delta);
			tick(delta);
			input();
			render();
			zpos += zspeed * delta;
			xpos += xspeed * delta;
			ypos += yspeed * delta;
			// System.out.println(getDelta());

			Display.update();
			Display.sync(60);
		}

		quit();
	}

	private void quit() {

		Display.destroy();
		System.exit(0);
	}

	private void addEntities() {
		for (int i = 0; i < NUM_STARS; i++) {
			addPoint((float) (Math.random() * STAR_FEILD_SIZE)
					- STAR_FEILD_SIZE / 2,
					(float) (Math.random() * STAR_FEILD_SIZE) - STAR_FEILD_SIZE
							/ 2, (float) (Math.random() * STAR_FEILD_SIZE)
							- STAR_FEILD_SIZE / 2);
		}
		for (int i = 0; i < NUM_TRAILERS; i++) {
			addMover((float) (Math.random() * STAR_FEILD_SIZE)
					- STAR_FEILD_SIZE / 2,
					(float) (Math.random() * STAR_FEILD_SIZE) - STAR_FEILD_SIZE
							/ 2, (float) (Math.random() * STAR_FEILD_SIZE)
							- STAR_FEILD_SIZE / 2, (float)(Math.random()-.5) * TRAILER_SPEED, (float)(Math.random()-.5) * TRAILER_SPEED, (float)(Math.random()-.5) * TRAILER_SPEED);
		}
		for(int i = 0; i < NUM_FACERS; i++){
			addFacer(1, 1, 1, new Vector3f(), new Vector3f());
		}
	}

	private void addPoint(float x, float y, float z) {
		Entity temp = new Entity("defaultpoint");
		temp.addComponent(new PointRenderComponent());
		temp.position = new Vector3f(x, y, z);
		entities.add(temp);
	}
	private void addMover(float x, float y, float z, float dx, float dy, float dz) {
		Entity temp = new Entity("defaultmover");
		temp.addComponent(new PointTrailRenderComponent(TRAIL_LENGTH, TRAIL_FADE)); //PointTrailRenderComponent doesn't work :O
		temp.addComponent(new MovementComponent(new Vector3f(dx, dy, dz)));
		temp.position = new Vector3f(x, y, z);
		entities.add(temp);
	}
	private void addFacer(float x, float y, float z, Vector3f dirMoving, Vector3f dirFacing){
		Entity temp = new Entity("defaultfacer");
		temp.addComponent(new FacingComponent(dirFacing));
		temp.addComponent(new MovementComponent(dirMoving));
		temp.position = new Vector3f(x, y, z);
		entities.add(temp);
	}

	private void tick(double delta) {
		for (Entity e : entities) {
			e.update((int) delta, entities);
		}
		interactions();
	}
	
	private void interactions() { // how objects react to each other

	}

	private void render() {
		// draw
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

		glMatrixMode(GL_PROJECTION);
		gluPerspective(FOV, ASPECT_RATIO, CLOSE_RENDER_LIM, FAR_RENDER_LIM);
		glMatrixMode(GL_MODELVIEW);

		glLoadIdentity();
		glTranslated(xpos, ypos, zpos);
		//
		// System.out.println("Hi!");
		for (Entity e : entities) {
			e.render();
			// System.out.println(e.getPosition().x);
		}
		// System.out.println(entities.isEmpty());

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

	private void input() {
		// check for input
		// Sample mouse and key usage

		if (mouseEnabled) {
			int mouseX = Mouse.getX();// - WIDTH / 2;
			int mouseY = Mouse.getY();// - HEIGHT / 2;
			if (Mouse.isButtonDown(0)) {
				quadX = 2 * mouseX - quadWidth / 2;
				quadY = 2 * mouseY - quadHeight / 2;
			}
			if (Mouse.isButtonDown(1) || Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
				if (mouseX > 0 && mouseY < HEIGHT - 1) {
					xspeed = Mouse.getDX();
					yspeed = Mouse.getDY();
					zspeed = Mouse.getDWheel();
				}
			}
			if (Mouse.isButtonDown(2)) {
				zspeed = Mouse.getDWheel();
			}

		}

		if (Keyboard.isKeyDown(Keyboard.KEY_E)) {
			mouseEnabled = true;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
			mouseEnabled = false;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
			// mouseEnabled = true;
			// zpos = 0;
			zspeed = 0;
			xspeed = 0;
			yspeed = 0;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_R)) {
			// mouseEnabled = false;
			// glLoadIdentity();
			zpos = 0;
			xpos = 0;
			ypos = 0;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
			quit();
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_F)) {
			zspeed += camAccel;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_V)) {
			zspeed -= camAccel;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_LEFT)) {
			xspeed += camAccel;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) {
			xspeed -= camAccel;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_DOWN)) {
			yspeed += camAccel;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_UP)) {
			yspeed -= camAccel;
		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new EntityTest();

	}

}
