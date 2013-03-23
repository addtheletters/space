package glTest;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL30.*;
import static org.lwjgl.opengl.GL15.*; //Vertex Buffer Array Rendering n'stuff
import static org.lwjgl.util.glu.GLU.*; //gluPerspective

import java.nio.FloatBuffer;
import java.util.*;

import org.lwjgl.opengl.*;
import org.lwjgl.util.Color;
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
	//Left click to move 2d white square
	
//S to stop all camera movement
//R to reset camera position to "center"


public class Test3D {

	// basic 3d 

	private boolean mouseEnabled = true;

	private final String TITLE = "Points!";

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
	}

	private void setUpTimer() {
		lastFrame = getTime();
	}

	private void setUpEntities() {
		toUpdate = new ArrayList<Tickable>();
		//toRender = new ArrayList<Renderable>();

		addEntities();
	}

	public static final int WIDTH = 640;
	public static final int HEIGHT = 480;

	// all objects that need updating
	public ArrayList<Tickable> toUpdate;
	//public ArrayList<Renderable> toRender;
	//Made obsolete by new VBO render code.
	
	final float FOV = 45f;
	final float ASPECT_RATIO = (float) WIDTH/HEIGHT;
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
	//for vertex buffer objects
	
	final int VERTEX_DIM = 3; //3 dimensions
	final int COLOR_DIM = 3; //no alpha, or it would be 4
	float zspeed = 0.0f;
	float xspeed = 0.0f;
	float yspeed = 0.0f;
	float quadX = 100;
	float quadY = 100;
	float quadWidth = 100;
	float quadHeight = 100;
	
	FloatBuffer pointVertexData;
	FloatBuffer lineVertexData;
	FloatBuffer lineColorData;
	
	public Test3D() {
		setUpDisplay();
		setUpOpenGL();

		setUpEntities();
		
		//setUpRenderBuffers();
		
		setUpTimer();
		

		while (!Display.isCloseRequested()) {
			// loop
			int delta = getDelta();
			
			tick();
			input();
			render();
			zpos += zspeed * delta / 10.0;
			xpos += xspeed * delta / 10.0;
			ypos += yspeed * delta / 10.0;
			// System.out.println(getDelta());

			Display.update();
			Display.sync(60);
		}
		
		quit();
	}

	private void quit() {
		glDeleteBuffers(vboPointVertexHandle);
		glDeleteBuffers(vboLineVertexHandle);
		glDeleteBuffers(vboLineColorHandle);
		Display.destroy();
		System.exit(0);
	}

	private void setUpRenderBuffers() {
		pointVertexData = BufferUtils.createFloatBuffer(NUM_POINTS * VERTEX_DIM);
		lineVertexData = BufferUtils.createFloatBuffer(NUM_LINE_VERTICES * VERTEX_DIM);
		lineColorData = BufferUtils.createFloatBuffer(NUM_LINE_VERTICES * COLOR_DIM);
	}
	private void flipRenderBuffers(){
		pointVertexData.flip();
		lineVertexData.flip();
		lineColorData.flip();
	}

	private void addEntities() { //adds points, render buffers
		setUpRenderBuffers();
		
		Random random = new Random();
		Point last = new Point((random.nextFloat() - 0.5f)*POINTS_XDISTR,
				(random.nextFloat() - 0.5f)*POINTS_YDISTR, random.nextInt((int)POINTS_ZDISTR) -POINTS_ZDISTR);
		int linecount = 0;
		for (int i = 0; i < NUM_POINTS; i++) {
			Point p = new Point((random.nextFloat() - 0.5f)*POINTS_XDISTR,
					(random.nextFloat() - 0.5f)*POINTS_YDISTR, random.nextInt((int)POINTS_ZDISTR) - POINTS_ZDISTR);
			
			pointVertexData.put(new float[]{p.x, p.y, p.z});
			
			toUpdate.add(p);
			//toRender.add(p);
			
			
			if( i % 1000 == 0 && linecount < NUM_LINES){//random.nextFloat() * 1000 < 1){
				linecount ++;
				Line l = new Line(p, last);
				
				lineVertexData.put(new float[]{l.p1.x, l.p1.y, l.p1.z});
				lineVertexData.put(new float[]{l.p2.x, l.p2.y, l.p2.z});
				lineColorData.put(new float[]{l.color1.getRed()/255.0f, l.color1.getBlue()/255.0f, l.color1.getGreen()/255.0f}); 
				lineColorData.put(new float[]{l.color2.getRed()/255.0f, l.color2.getBlue()/255.0f, l.color2.getGreen()/255.0f});
				
				
				toUpdate.add(l);
				//toRender.add(l);
			}
			last = p;
		}
		
		
		flipRenderBuffers();
		setUpBufferHandles();
		//System.out.println(linecount);
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
		gluPerspective(FOV, ASPECT_RATIO, CLOSE_RENDER_LIM, FAR_RENDER_LIM);
		glMatrixMode(GL_MODELVIEW);
		
		
		glLoadIdentity();
		glTranslated(xpos, ypos, zpos);
		
		//Vertex Buffer Object mode for points n lines
		//Let's go! Yeah! How do you even do this? I don't know!
		
		glEnableClientState(GL_VERTEX_ARRAY);
		glBindBuffer(GL_ARRAY_BUFFER, vboPointVertexHandle);
		glVertexPointer(VERTEX_DIM, GL_FLOAT, 0, 0L);
		
		glDrawArrays(GL_POINTS, 0, NUM_POINTS);
		
		glBindBuffer(GL_ARRAY_BUFFER, 0);
		glDisableClientState(GL_VERTEX_ARRAY);
		
		
		//Lines
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
		//End of VBO render code
		
		
		//Immediate mode for points n lines
		/*
		glBegin(GL_POINTS);
		Point p = new Point(0, 0, 0);
		for (Renderable o : toRender) {
			if(o.getClass() == p.getClass())
				o.render();
		}
		glEnd();
		glBegin(GL_LINES);
		Line l = new Line(new Point(0, 0, 0) , new Point(0, 0, 0));
		for (Renderable o : toRender) {
			if(o.getClass() == l.getClass())
				o.render();
		
		}
		glEnd(); */
		
		
		//Reed.... What teh crap does this do XD
		
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		////glOrtho(0, WIDTH, 0, HEIGHT, -1, 1);
		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();
		
		
		//Immediate mode for the overlay quad
		/*
		float xMin = quadX/WIDTH -1.f;
		float yMin = quadY/HEIGHT - 1.f;
		float xMax = (quadX + quadWidth)/WIDTH - 1.f;
		float yMax = (quadY + quadHeight)/HEIGHT - 1.f;
		
		//glDisable(GL_DEPTH_TEST);
		glBegin(GL_QUADS);
			glColor3f(1f, 1f, 1f);
			glVertex2f(xMin, yMin);
			glVertex2f(xMin, yMax);
			glVertex2f(xMax, yMax);
			glVertex2f(xMax, yMin);
		glEnd();
		*/
		
		//Errors?
		//int error = glGetError();
		//if (error != GL_NO_ERROR) {
		//	System.out.println(gluGetString(error));
		//}
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
			if (Mouse.isButtonDown(1) || Mouse.isButtonDown(2) || Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
				if(mouseX > 0 && mouseY < HEIGHT - 1){
					xspeed = Mouse.getDX();
					yspeed = Mouse.getDY();
					zspeed = Mouse.getDWheel();
				}
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
			zspeed = 0;
			xspeed = 0;
			yspeed = 0;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_R)) {
			//mouseEnabled = false;
			//glLoadIdentity();
			zpos = 0;
			xpos = 0;
			ypos = 0;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
			quit();
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_F)) {
			zspeed += 0.1f;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_V)) {
			zspeed -= 0.1f;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_LEFT)) {
			xspeed += 0.1f;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) {
			xspeed -= 0.1f;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_DOWN)) {
			yspeed += 0.1f;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_UP)) {
			yspeed -= 0.1f;
		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Test3D();

	}

}

class Line implements Tickable, Renderable {
	Point p1;
	Point p2;
	Color color1;
	Color color2;
	public Line(Point p1, Point p2){
		super();
		this.p1 = p1;
		this.p2 = p2;
		color1 = new Color((int)(Math.random() * 255), (int)(Math.random() * 255), (int)(Math.random() * 255));
		color2 = new Color((int)(Math.random() * 255), (int)(Math.random() * 255), (int)(Math.random() * 255));
	}
	@Override
	public void render() {
		// TODO Auto-generated method stub
		//glBegin(GL_LINES);
		//System.out.println(color1);
		glColor3f(color1.getRed()/255.0f, color1.getBlue()/255.0f, color1.getGreen()/255.0f);
		glVertex3f(p1.x, p1.y, p1.z);
		glColor3f(color2.getRed()/255.0f, color2.getBlue()/255.0f, color2.getGreen()/255.0f);
		glVertex3f(p2.x, p2.y, p2.z);
		//glEnd();
		
	}
	@Override
	public void step(int delta) {
		// TODO Auto-generated method stub
		
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
		//glBegin(GL_POINTS);
		glColor3f(1f, 1f, 1f);
		glVertex3f(x, y, z);
		//glEnd();
		
		
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
