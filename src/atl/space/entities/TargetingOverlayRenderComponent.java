package atl.space.entities;

import static org.lwjgl.opengl.GL11.GL_LINE_STRIP;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glColor4f;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glVertex2f;

import static org.lwjgl.opengl.GL11.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.FloatBuffer;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.Color;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

public class TargetingOverlayRenderComponent extends
		EquiTriangleOverlayRenderComponent {

	private Color color;
	private int size;

	private Texture texture = null;

	private boolean shouldRender = false;

	public TargetingOverlayRenderComponent(Color color) {
		super(color, 20);

		this.color = color;
		this.size = 20;
	}

	public TargetingOverlayRenderComponent(Color color, String uri,
			String format) {
		super(color, 20);

		this.color = color;
		this.size = 20;
		try {
			setTexture(uri, format);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			Display.destroy();
			System.exit(1);
		} catch (IOException e) {
			e.printStackTrace();
			Display.destroy();
			System.exit(1);
		}

	}

	public void setTexture(String uri, String format)
			throws FileNotFoundException, IOException {
		texture = TextureLoader.getTexture(format, new FileInputStream(
				new File(uri)));
	}

	public void render() {

		FloatBuffer winpos = getWinPos();

		float windowX = winpos.get(0);
		float windowY = winpos.get(1);

		float mouseX = Mouse.getX();
		float mouseY = Mouse.getY();

		if (winpos.get(2) > 0 && winpos.get(2) < 1) {
			setUp2D();

			if (Math.abs((int) (mouseX - windowX)) < size
					&& Math.abs((int) (mouseY - windowY)) < size) {
				if (Mouse.isButtonDown(0)) {
					shouldRender = true;
					//renderImage(0, 0);
					// renderBox(windowX,windowY);
				}
				renderHighlight(windowX, windowY);
			} else {
				renderTriangle(windowX, windowY);
			}

			if (shouldRender) {
				renderImage(0, 0);
			}

			backTo3D();
		}
	}

	/***
	 * Renders the overlay as a triangle w/o any special characteristics
	 * 
	 * @param windowX
	 * @param windowY
	 */
	public void renderTriangle(float windowX, float windowY) {
		glColor4f(color.getRed() / 255.0f, color.getGreen() / 255.0f,
				color.getBlue() / 255.0f, color.getAlpha() / 255.0f);
		glBegin(GL_LINE_STRIP);

		glVertex2f(windowX - size / 2,
				(float) (windowY - (size / 2) / Math.sqrt(3)));
		glVertex2f(
				windowX,
				(float) (windowY + (Math.sqrt(3) / 2) * (size) - ((size / 2) / Math
						.sqrt(3))));
		glVertex2f(windowX + size / 2,
				(float) (windowY - (size / 2) / Math.sqrt(3)));
		glVertex2f(windowX - size / 2,
				(float) (windowY - (size / 2) / Math.sqrt(3)));

		glEnd();
	}

	public void renderHighlight(float windowX, float windowY) {
		glColor4f(255.0f, 0.0f, 0.0f, 255.0f);

		glBegin(GL_LINE_STRIP);

		glVertex2f((float) (windowX - size / 2), (float) (windowY - (size / 2)));
		glVertex2f((float) (windowX - size / 2), (float) (windowY + (size / 2)));
		glVertex2f((float) (windowX + size / 2), (float) (windowY + (size / 2)));
		glVertex2f((float) (windowX + size / 2), (float) (windowY - (size / 2)));
		glVertex2f((float) (windowX - size / 2), (float) (windowY - (size / 2)));

		glEnd();
	}

	public void renderBox(float windowX, float windowY) {
		glColor4f(255.0f, 0.0f, 0.0f, 255.0f);

		glBegin(GL_LINE_STRIP);

		glVertex2f((float) (windowX - size / 2), (float) (windowY - (size / 2)));
		glVertex2f((float) (windowX - size / 2), (float) (windowY + (size / 2)));
		glVertex2f((float) (windowX + size / 2), (float) (windowY + (size / 2)));
		glVertex2f((float) (windowX + size / 2), (float) (windowY - (size / 2)));
		glVertex2f((float) (windowX - size / 2), (float) (windowY - (size / 2)));

		glEnd();
	}

	public void renderImage(int x, int y) {

		glBegin(GL_QUADS);

		texture.bind();

		glTexCoord2f(1, 1);
		glVertex2f(000, 000);
		glTexCoord2f(0, 1);
		glVertex2f(200, 000);
		glTexCoord2f(0, 0);
		glVertex2f(200, 200);
		glTexCoord2f(1, 0);
		glVertex2f(000, 200);

		glEnd();

		texture.release();
	}

	public void renderImage(String uri, String format, int x, int y) {

		try {
			setTexture(uri, format);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			Display.destroy();
			System.exit(1);
		} catch (IOException e) {
			e.printStackTrace();
			Display.destroy();
			System.exit(1);
		}

		glBegin(GL_QUADS);

		texture.bind();

		glTexCoord2f(1, 1);
		glVertex2f(000, 000);
		glTexCoord2f(0, 1);
		glVertex2f(200, 000);
		glTexCoord2f(0, 0);
		glVertex2f(200, 200);
		glTexCoord2f(1, 0);
		glVertex2f(000, 200);

		glEnd();

		texture.release();
	}

}
