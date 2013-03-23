package glTest;

import static org.lwjgl.opengl.GL11.*;

import static org.lwjgl.openal.AL10.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;

import org.lwjgl.opengl.*;
import org.lwjgl.*;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import org.lwjgl.openal.*;
import org.lwjgl.util.WaveData;

//Testing openAL sound stuff
//leftclick to make laser noises

public class OpenALTest {

	// basic 2d blank

	private boolean mouseEnabled = true;

	private final String TITLE = "Sound?";

	final int NUM_SOUNDS = 1;
	final int NUM_SOUND_BYTES = NUM_SOUNDS * 4;
	//Sound buffer int handlers
	IntBuffer soundbuffer = ByteBuffer.allocateDirect(NUM_SOUND_BYTES).asIntBuffer();
	IntBuffer sourcebuffer = ByteBuffer.allocateDirect(NUM_SOUND_BYTES).asIntBuffer();
	//deletion requires direct buffers, so bytebuffers morphed to intbuffers
	
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
	
	private void setUpOpenAL(){
		try {
			AL.create();
			addSound("Laser1.wav");
			
			soundbuffer.flip();
			sourcebuffer.flip();
			
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
		
	}
	
	private void addSound(String filename){
		try {
			WaveData data = WaveData.create(new FileInputStream("res/" + filename));
			int buffer = alGenBuffers();
			alBufferData(buffer, data.format, data.data, data.samplerate);
			soundbuffer.put(buffer);
			data.dispose();
			int source = alGenSources();
			alSourcei(source, AL_BUFFER, buffer);
			sourcebuffer.put(source);
			
		} catch (FileNotFoundException e) {
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

	int delta = 0;
	// all objects that need updating
	public ArrayList<Tickable> toUpdate;
	public ArrayList<Renderable> toRender;

	public OpenALTest() {
		setUpDisplay();
		setUpOpenAL();
		setUpOpenGL();

		setUpEntities();

		setUpTimer();

		while (!Display.isCloseRequested()) {
			// loop
			delta = getDelta();
			tick();
			input();
			render();
			
			Display.update();
			Display.sync(60);
		}
		
		quit();
		
	}
	
	private void quit() {
		alDeleteBuffers(soundbuffer);
		alDeleteSources(sourcebuffer);
		AL.destroy();
		Display.destroy();
		System.exit(0);
		//but... why error.... why
	}

	private void addEntities() {
		//toUpdate.add(stuff);
		//toRender.add(stuff);
	}

	private void tick() {
		for (Tickable o : toUpdate) {
			o.step(delta);
		}
		interactions();
	}

	private void interactions() { // how objects react to each other

	}

	private void render() {
		// draw
		glClear(GL_COLOR_BUFFER_BIT);// | GL_DEPTH_BUFFER_BIT);
		for (Renderable o : toRender) {
			o.render();
		}

	}

	private void input() {
		// check for input
		// Sample mouse and key usage
		if (mouseEnabled) {
			//int mouseX = Mouse.getX();// - WIDTH / 2;
			//int mouseY = Mouse.getY();// - HEIGHT / 2;
			if (Mouse.isButtonDown(0)) {
				//PLAY LAZORS
				alSourcePlay(sourcebuffer.get(0));
				//might wanna get some hashmaps in dis ****
			}
			if (Mouse.isButtonDown(1)) {
				
			}
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_E)) {
			mouseEnabled = true;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
			mouseEnabled = false;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
			quit();
		}
		

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new OpenALTest();

	}

}
