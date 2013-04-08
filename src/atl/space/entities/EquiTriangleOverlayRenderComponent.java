package atl.space.entities;

import static org.lwjgl.opengl.GL11.GL_LINE_STRIP;

import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glColor4f;
import static org.lwjgl.opengl.GL11.glEnd;

import static org.lwjgl.opengl.GL11.glVertex2f;

import java.nio.FloatBuffer;

import org.lwjgl.util.Color;


public class EquiTriangleOverlayRenderComponent extends
		Overlay2DRenderComponent {
	
	private Color color;
	private float size;
	//size is the length of one side of the triangle
	
	private static float WIDTH = 640;
	private static float HEIGHT = 480;

	// private static float ASPECTRATIO = (float)(WIDTH/HEIGHT);

	public EquiTriangleOverlayRenderComponent() {
		super();
		color = new Color();
		size = 0;
	}

	public EquiTriangleOverlayRenderComponent(Color color, float size) {
		super();
		// setOrthoProjMatrix(createOrthoMatrix(-ASPECTRATIO * HEIGHT/2,
		// ASPECTRATIO * HEIGHT/2, -1 * HEIGHT/2, 1 * HEIGHT/2, 1,-1));
		setOrthoProjMatrix(createOrthoMatrix(0, WIDTH, 0, HEIGHT, 1, -1));
		this.color = color;
		this.size = size;
	}

	public void render() {
		FloatBuffer winpos = getWinPos();

		float windowX = winpos.get(0);
		float windowY = winpos.get(1);

		if (winpos.get(2) > 0 && winpos.get(2) < 1) {
			setUp2D();

			glColor4f(color.getRed() / 255.0f, color.getGreen() / 255.0f,
					color.getBlue() / 255.0f, color.getAlpha() / 255.0f);
			glBegin(GL_LINE_STRIP);
			//TODO make this draw triangles
			
			glVertex2f(windowX - size/2,  (float)(windowY - (size/2) / Math.sqrt(3)));
			glVertex2f(windowX, (float)(windowY + (Math.sqrt(3)/2)*(size) - ((size/2) / Math.sqrt(3))));
			glVertex2f(windowX + size / 2, (float)(windowY - (size/2) / Math.sqrt(3)));
			glVertex2f(windowX - size/2,  (float)(windowY - (size/2) / Math.sqrt(3)));
			
			glEnd();
			backTo3D();
		}
	}
	
}
