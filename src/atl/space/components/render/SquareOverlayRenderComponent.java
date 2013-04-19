package atl.space.components.render;

import static org.lwjgl.opengl.GL11.*;
//import static org.lwjgl.util.glu.GLU.*;

import java.nio.FloatBuffer;
//import java.nio.IntBuffer;

import org.lwjgl.util.Color;


//import utility.BufferTools;

public class SquareOverlayRenderComponent extends Overlay2DRenderComponent {
	private Color color;
	private float size;

	private static float WIDTH = 640;
	private static float HEIGHT = 480;

	// private static float ASPECTRATIO = (float)(WIDTH/HEIGHT);

	public SquareOverlayRenderComponent() {
		super();
		color = new Color();
		size = 0;
	}

	public SquareOverlayRenderComponent(Color color, float size) {
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
			// glTranslatef(winpos.get(0), winpos.get(1), winpos.get(2));

			// System.out.println(owner.position);
			// System.out.println(winpos.get(0) + " " + winpos.get(1) +" "+
			// winpos.get(2) );
			// System.out.println("X: " + windowX + " Y: " + windowY);

			glColor4f(color.getRed() / 255.0f, color.getGreen() / 255.0f,
					color.getBlue() / 255.0f, color.getAlpha() / 255.0f);
			glBegin(GL_LINE_STRIP);
			// translatedx = screen representation of owner.position
			// translatedy = screen representation of owner.position

			glVertex2f(windowX - size / 2, windowY - size / 2);
			glVertex2f(windowX - size / 2, windowY + size / 2);
			glVertex2f(windowX + size / 2, windowY + size / 2);
			glVertex2f(windowX + size / 2, windowY - size / 2);
			glVertex2f(windowX - size / 2, windowY - size / 2);
			// glVertex2f(translatedx - size/2, translatedy - size/2);
			// glVertex2f(translatedx - size/2, translatedy + size/2);
			// glVertex2f(translatedx + size/2, translatedy + size/2);
			// glVertex2f(translatedx + size/2, translatedy - size/2);
			// glVertex2f(translatedx - size/2, translatedy - size/2);
			glEnd();
			backTo3D();
		}
	}

}
