package atl.space.entities;

import static org.lwjgl.opengl.GL11.*;

import java.nio.FloatBuffer;
import java.util.List;

import utility.BufferTools;

public class Overlay2DRenderComponent extends RenderableComponent {
	
	//private FloatBuffer perspectiveProjectionMatrix = BufferTools
			//.reserveDataf(16);
	private FloatBuffer orthographicProjectionMatrix = BufferTools
			.reserveDataf(16);
	
	public Overlay2DRenderComponent(){
		//do camera setup before this
		setOrthoProjMatrix(createOrthoMatrix(-1, 1, -1, 1, 1, -1));
	}
	public Overlay2DRenderComponent(FloatBuffer orthoProjMatrix){
		setOrthoProjMatrix(orthoProjMatrix);
	}
	
	public void setOrthoProjMatrix(FloatBuffer orthoProjMatrix){
		orthographicProjectionMatrix = orthoProjMatrix;
	}
	
	public static FloatBuffer createOrthoMatrix(float left, float right, float bottom, float top, 
			float near, float far){
		FloatBuffer orthoProjMatrix = BufferTools
				.reserveDataf(16);
		glPushMatrix();
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(left, right, bottom, top, near, far);
		glGetFloat(GL_PROJECTION_MATRIX, orthoProjMatrix);
		glPopMatrix();
		glMatrixMode(GL_MODELVIEW);
		return orthoProjMatrix;
	}
	
	@Override
	public void render() {
		//implementations of render will use setUp2d and backTo3d
	}
	
	public void setUp2D(){ //args for the orthographic matrix?
		//glUseProgram(0);
		glMatrixMode(GL_PROJECTION);
		glPushMatrix();
		//glLoadIdentity();
		glLoadMatrix(orthographicProjectionMatrix);
		glMatrixMode(GL_MODELVIEW);
		glPushMatrix();
		glLoadIdentity();
		//draw
	}
	public void backTo3D(){ //args for the projection matrix?
		//assumes in modelview matrix mode
		glPopMatrix();
		glMatrixMode(GL_PROJECTION);
		glPopMatrix();
		//glLoadMatrix(perspectiveProjectionMatrix);
		glMatrixMode(GL_MODELVIEW);
	}

	@Override
	public void update(int delta, List<Entity> entities) {
		//do nothing? may need to do stuff in subclasses
	}

	@Override
	public Component getStepped(int delta, List<Entity> entities) {
		//short answer: no.
		// I don't even know.
		return null;
	}

}
