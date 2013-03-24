package glTest;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*; //GLSL shaders?
//import static org.lwjgl.opengl.GL30.*;
import static org.lwjgl.opengl.GL15.*; //Vertex Buffer Array Rendering n'stuff
import static org.lwjgl.util.glu.GLU.*; //gluPerspective

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.FloatBuffer;
import java.util.*;
import utility.*;

import org.lwjgl.opengl.*;
//import org.lwjgl.util.Color;
import org.lwjgl.*;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import glTest.Line;
import glTest.Point;

//import tk.sritwinkles.Point;

//RK4 okay.

//Shaders use shader.vert and shader.frag

//WASD or arrow keys to move on XZ
//Space and lShift to go up/down
//Click to use mouse, right click to stop using mouse
	//Use mouse to look

public class TestGraphics {

	// basic 3d
	private static Camera camera;

	//private boolean mouseEnabled = true;

	private final String TITLE = "Testing Effects, Shaders, and Moving Objects";

	// frame independent movement speed using delta
	private long lastFrame;
	int delta;
	/*
	private double zpos;
	private double xpos;
	private double ypos;
	 */
	// Shader variables
	int shaderProgram;
	int vertexShader;
	int fragmentShader;
	StringBuilder vertexShaderSource;
	StringBuilder fragmentShaderSource;

	private void setUpShaders() {
		shaderProgram = glCreateProgram();
		vertexShader = glCreateShader(GL_VERTEX_SHADER);
		fragmentShader = glCreateShader(GL_FRAGMENT_SHADER);
		vertexShaderSource = new StringBuilder();
		fragmentShaderSource = new StringBuilder();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(
					"src/glTest/shader.vert"));
			String line;
			while ((line = reader.readLine()) != null) {
				vertexShaderSource.append(line).append("\n");
			}
			reader.close();

			reader = new BufferedReader(
					new FileReader("src/glTest/shader.frag"));
			while ((line = reader.readLine()) != null) {
				fragmentShaderSource.append(line).append("\n");
			}
			reader.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("Shaders not loaded properly.");
			Display.destroy();
			System.exit(1);
		}

		glShaderSource(vertexShader, vertexShaderSource);
		glShaderSource(fragmentShader, fragmentShaderSource);
		glCompileShader(vertexShader);
		glCompileShader(fragmentShader);
		if (glGetShaderi(vertexShader, GL_COMPILE_STATUS) == GL_FALSE) {
			System.err.println("Vertex shader failed to compile.");
		}
		if (glGetShaderi(fragmentShader, GL_COMPILE_STATUS) == GL_FALSE) {
			System.err.println("Fragment shader failed to compile.");
		}

		glAttachShader(shaderProgram, vertexShader);
		glAttachShader(shaderProgram, fragmentShader);
		glLinkProgram(shaderProgram);
		glValidateProgram(shaderProgram);

	}

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
			System.exit(1);
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
	}

	private void setUpTimer() {
		lastFrame = getTime();
	}

	private void setUpEntities() {
		toUpdate = new ArrayList<Tickable>();
		// toRender = new ArrayList<Renderable>();

		addEntities();
	}

	public static final int WIDTH = 640;
	public static final int HEIGHT = 480;

	// all objects that need updating
	public ArrayList<Tickable> toUpdate;
	// public ArrayList<Renderable> toRender;
	// Made obsolete by new VBO render code.

	final float FOV = 45f;
	final float ASPECT_RATIO = (float) WIDTH / HEIGHT;
	final float CLOSE_RENDER_LIM = 0.001f;
	final float FAR_RENDER_LIM = 6000;

	final float POINTS_XDISTR = 23000f;
	final float POINTS_YDISTR = 23000f;
	final float POINTS_ZDISTR = 28000f;

	final int NUM_POINTS = 100000;
	final int NUM_LINES = 105;
	final int NUM_LINE_VERTICES = NUM_LINES * 2;
	int vboPointVertexHandle;
	int vboLineVertexHandle;
	int vboLineColorHandle;
	// for vertex buffer objects

	final int VERTEX_DIM = 3; // 3 dimensions
	final int COLOR_DIM = 3; // no alpha, or it would be 4

	final float camAccel = 0.4f;
	float zspeed = 0.0f;
	float xspeed = 0.0f;
	float yspeed = 0.0f;
	float quadX = 100;
	float quadY = 100;
	float quadWidth = 100;
	float quadHeight = 100;
	
	//double delta;
	
	FloatBuffer pointVertexData;
	FloatBuffer lineVertexData;
	FloatBuffer lineColorData;

	public TestGraphics() {
		setUpDisplay();
		setUpOpenGL();
		setUpShaders();
		setUpEntities();
		setUpCamera();

		// Render buffers set up in setUpEntities()

		setUpTimer();

		while (!Display.isCloseRequested()) {
			// loop
			delta = getDelta();
			//System.out.println(delta);
			
			tick(delta);
			input();
			render();
			//zpos += zspeed * delta;
			//xpos += xspeed * delta;
			//ypos += yspeed * delta;
			// System.out.println(getDelta());

			Display.update();
			Display.sync(60);
		}

		quit();
	}

	private void setUpCamera() {
		camera = new FlyCamera.Builder()
				.setAspectRatio(
						ASPECT_RATIO)
				.setRotation(0f, 0f, 0f)
				.setPosition(0f, 0f, 0f).setFieldOfView(FOV).build();
		camera.applyOptimalStates();
		camera.applyPerspectiveMatrix();
	}

	private void quit() {
		glDeleteBuffers(vboPointVertexHandle);
		glDeleteBuffers(vboLineVertexHandle);
		glDeleteBuffers(vboLineColorHandle);
		glDeleteShader(vertexShader);
		glDeleteShader(fragmentShader);
		glDeleteProgram(shaderProgram);
		Display.destroy();
		System.exit(0);
	}

	private void setUpRenderBuffers() {
		pointVertexData = BufferUtils
				.createFloatBuffer(NUM_POINTS * VERTEX_DIM);
		lineVertexData = BufferUtils.createFloatBuffer(NUM_LINE_VERTICES
				* VERTEX_DIM);
		lineColorData = BufferUtils.createFloatBuffer(NUM_LINE_VERTICES
				* COLOR_DIM);
	}

	private void flipRenderBuffers() {
		pointVertexData.flip();
		lineVertexData.flip();
		lineColorData.flip();
	}

	private void addEntities() { // adds points, render buffers

		setUpRenderBuffers();

		Random random = new Random();
		Point last = new Point((random.nextFloat() - 0.5f) * POINTS_XDISTR,
				(random.nextFloat() - 0.5f) * POINTS_YDISTR,
				random.nextInt((int) POINTS_ZDISTR) - POINTS_ZDISTR);
		int linecount = 0;
		for (int i = 0; i < NUM_POINTS; i++) {
			Point p = new Point((random.nextFloat() - 0.5f) * POINTS_XDISTR,
					(random.nextFloat() - 0.5f) * POINTS_YDISTR,
					random.nextInt((int) POINTS_ZDISTR) - POINTS_ZDISTR);

			pointVertexData.put(new float[] { p.x, p.y, p.z });

			toUpdate.add(p);
			// toRender.add(p);

			if (i % 1000 == 0 && linecount < NUM_LINES) {// random.nextFloat() *
															// 1000 < 1){
				linecount++;
				Line l = new Line(p, last);

				lineVertexData.put(new float[] { l.p1.x, l.p1.y, l.p1.z });
				lineVertexData.put(new float[] { l.p2.x, l.p2.y, l.p2.z });
				lineColorData.put(new float[] { l.color1.getRed() / 255.0f,
						l.color1.getBlue() / 255.0f,
						l.color1.getGreen() / 255.0f });
				lineColorData.put(new float[] { l.color2.getRed() / 255.0f,
						l.color2.getBlue() / 255.0f,
						l.color2.getGreen() / 255.0f });

				toUpdate.add(l);
				// toRender.add(l);
			}
			last = p;
		}

		flipRenderBuffers();
		setUpBufferHandles();
		// System.out.println(linecount);
	}

	private void setUpBufferHandles() {
		vboPointVertexHandle = glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, vboPointVertexHandle);
		glBufferData(GL_ARRAY_BUFFER, pointVertexData, GL_STATIC_DRAW);
		glBindBuffer(GL_ARRAY_BUFFER, 0);
		vboLineVertexHandle = glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, vboLineVertexHandle);
		glBufferData(GL_ARRAY_BUFFER, lineVertexData, GL_STATIC_DRAW);
		glBindBuffer(GL_ARRAY_BUFFER, 0);
		vboLineColorHandle = glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, vboLineColorHandle);
		glBufferData(GL_ARRAY_BUFFER, lineColorData, GL_STATIC_DRAW);
		glBindBuffer(GL_ARRAY_BUFFER, 0);

	}

	private void tick(double delta) {
		for (Tickable o : toUpdate) {
			o.step((int)delta);
		}
		interactions();
	}

	private void interactions() { // how objects react to each other

	}

	private void render() {
		//camera?
		// draw
        
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		
		glLoadIdentity();
        camera.applyTranslations();
		
		glMatrixMode(GL_PROJECTION);
		gluPerspective(FOV, ASPECT_RATIO, CLOSE_RENDER_LIM, FAR_RENDER_LIM);
		glMatrixMode(GL_MODELVIEW);

		/*glLoadIdentity();
		glTranslated(xpos, ypos, zpos);
		*/

		// Shaders!
		glUseProgram(shaderProgram);

		// glUseProgram(0);

		// Vertex Buffer Object mode for points n lines
		// Let's go! Yeah! How do you even do this? I don't know!
		glEnableClientState(GL_VERTEX_ARRAY);
		glBindBuffer(GL_ARRAY_BUFFER, vboPointVertexHandle);
		glVertexPointer(VERTEX_DIM, GL_FLOAT, 0, 0L);

		glDrawArrays(GL_POINTS, 0, NUM_POINTS);

		glBindBuffer(GL_ARRAY_BUFFER, 0);
		glDisableClientState(GL_VERTEX_ARRAY);

		// Lines
		glEnableClientState(GL_VERTEX_ARRAY);
		glEnableClientState(GL_COLOR_ARRAY);

		glBindBuffer(GL_ARRAY_BUFFER, vboLineVertexHandle);
		glVertexPointer(VERTEX_DIM, GL_FLOAT, 0, 0L);
		glBindBuffer(GL_ARRAY_BUFFER, vboLineColorHandle);
		glColorPointer(COLOR_DIM, GL_FLOAT, 0, 0L);

		glDrawArrays(GL_LINES, 0, NUM_LINE_VERTICES);

		glBindBuffer(GL_ARRAY_BUFFER, 0);
		glDisableClientState(GL_VERTEX_ARRAY);
		glDisableClientState(GL_COLOR_ARRAY);
		// End of VBO render code

		// Immediate mode for points n lines
		/*
		 * glBegin(GL_POINTS); Point p = new Point(0, 0, 0); for (Renderable o :
		 * toRender) { if(o.getClass() == p.getClass()) o.render(); } glEnd();
		 * glBegin(GL_LINES); Line l = new Line(new Point(0, 0, 0) , new
		 * Point(0, 0, 0)); for (Renderable o : toRender) { if(o.getClass() ==
		 * l.getClass()) o.render();
		 * 
		 * } glEnd();
		 */

		// Reed.... What teh crap does this do XD

		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		// //glOrtho(0, WIDTH, 0, HEIGHT, -1, 1);
		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();

		// Errors?
		// int error = glGetError();
		// if (error != GL_NO_ERROR) {
		// System.out.println(gluGetString(error));
		// }
	}

	private void input() {
		// check for input
		// Sample mouse and key usage
		
		//using Camera
		
        camera.processKeyboard(16f, 50);
        if (Mouse.isButtonDown(0)) {
            Mouse.setGrabbed(true);
        } else if (Mouse.isButtonDown(1)) {
            Mouse.setGrabbed(false);
        }
        if(Mouse.isGrabbed()){
        	camera.processMouse(0.5f, 80, -80);
        }
		
		// Camera-less, old code
		/*
		 * if (mouseEnabled) { int mouseX = Mouse.getX();// - WIDTH / 2; int
		 * mouseY = Mouse.getY();// - HEIGHT / 2; if (Mouse.isButtonDown(0)) {
		 * quadX = 2*mouseX - quadWidth/2; quadY = 2*mouseY - quadHeight/2; } if
		 * (Mouse.isButtonDown(1) || Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
		 * if(mouseX > 0 && mouseY < HEIGHT - 1){ xspeed = Mouse.getDX(); yspeed
		 * = Mouse.getDY(); zspeed = Mouse.getDWheel(); } }
		 * if(Mouse.isButtonDown(2)){ zspeed = Mouse.getDWheel(); }
		 * 
		 * }
		 * 
		 * if (Keyboard.isKeyDown(Keyboard.KEY_E)) { mouseEnabled = true; } if
		 * (Keyboard.isKeyDown(Keyboard.KEY_D)) { mouseEnabled = false; } if
		 * (Keyboard.isKeyDown(Keyboard.KEY_S)) { //mouseEnabled = true; //zpos
		 * = 0; zspeed = 0; xspeed = 0; yspeed = 0; } if
		 * (Keyboard.isKeyDown(Keyboard.KEY_R)) { //mouseEnabled = false;
		 * //glLoadIdentity(); zpos = 0; xpos = 0; ypos = 0; }
		 */
         if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) { quit(); } 
        /*if
		 * (Keyboard.isKeyDown(Keyboard.KEY_F)) { zspeed += camAccel; } if
		 * (Keyboard.isKeyDown(Keyboard.KEY_V)) { zspeed -= camAccel; } if
		 * (Keyboard.isKeyDown(Keyboard.KEY_LEFT)) { xspeed += camAccel; } if
		 * (Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) { xspeed -= camAccel; } if
		 * (Keyboard.isKeyDown(Keyboard.KEY_DOWN)) { yspeed += camAccel; } if
		 * (Keyboard.isKeyDown(Keyboard.KEY_UP)) { yspeed -= camAccel; }
		 */
	}

	public static void main(String[] args) {
		new TestGraphics();

	}

}