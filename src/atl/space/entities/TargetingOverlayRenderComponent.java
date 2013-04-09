package atl.space.entities;

import static org.lwjgl.opengl.GL11.GL_LINE_STRIP;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glColor4f;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glVertex2f;

import org.lwjgl.opengl.GL11;

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

public class TargetingOverlayRenderComponent extends EquiTriangleOverlayRenderComponent {
	
	private Color color;
	private int size;
	
	private Texture texture = null;
	
	private boolean shouldRender = false;
	
	public TargetingOverlayRenderComponent(Color color) {
		super(color, 20);
		
		this.color = color;
		this.size = 20;
	}

	public void render() {
		
		FloatBuffer winpos = getWinPos();

		float windowX = winpos.get(0);
		float windowY = winpos.get(1);
		
		float mouseX = Mouse.getX();
		float mouseY = Mouse.getY();

		if (winpos.get(2) > 0 && winpos.get(2) < 1) {
			setUp2D();
			
			if ( Math.abs( (int)(mouseX-windowX) ) < size && Math.abs( (int)(mouseY-windowY) ) < size) {
				if (Mouse.isButtonDown(0)) {
					shouldRender = true;
					renderImage("res/lena.jpg","JPG",0,0);
					//renderBox(windowX,windowY);
				}
				renderHighlight(windowX, windowY);
			} else {
				renderTriangle(windowX, windowY);
			}
			
			if (shouldRender) {
				renderImage("res/lena.jpg","JPG",0,0);
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
		//TODO make this draw triangles
		
		glVertex2f(windowX - size/2,  (float)(windowY - (size/2) / Math.sqrt(3)));
		glVertex2f(windowX, (float)(windowY + (Math.sqrt(3)/2)*(size) - ((size/2) / Math.sqrt(3))));
		glVertex2f(windowX + size / 2, (float)(windowY - (size/2) / Math.sqrt(3)));
		glVertex2f(windowX - size/2,  (float)(windowY - (size/2) / Math.sqrt(3)));
		
		glEnd();
	}
	
	public void renderHighlight(float windowX, float windowY) {
		glColor4f(255.0f, 0.0f, 0.0f, 255.0f);

		glBegin(GL_LINE_STRIP);
		//TODO make this draw triangles
		
		
		
		glVertex2f((float)(windowX - size/2),  (float)(windowY - (size/2)));
		glVertex2f((float)(windowX - size/2),  (float)(windowY + (size/2)));
		glVertex2f((float)(windowX + size/2),  (float)(windowY + (size/2)));
		glVertex2f((float)(windowX + size/2),  (float)(windowY - (size/2)));
		glVertex2f((float)(windowX - size/2),  (float)(windowY - (size/2)));
		
		glEnd();
	}
	public void renderBox(float windowX, float windowY) {
		glColor4f(255.0f, 0.0f, 0.0f, 255.0f);

		glBegin(GL_LINE_STRIP);
		//TODO make this draw triangles
		
		
		
		glVertex2f((float)(windowX - size/2),  (float)(windowY - (size/2)));
		glVertex2f((float)(windowX - size/2),  (float)(windowY + (size/2)));
		glVertex2f((float)(windowX + size/2),  (float)(windowY + (size/2)));
		glVertex2f((float)(windowX + size/2),  (float)(windowY - (size/2)));
		glVertex2f((float)(windowX - size/2),  (float)(windowY - (size/2)));
		
		glEnd();
	}
	public void renderImage(String uri, String format, int x, int y) {
		
		try {
			if (texture==null) {
				texture = TextureLoader.getTexture(format, new FileInputStream(new File(uri)));
			} else {
				//System.out.println(texture);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			Display.destroy();
			System.exit(1);
		} catch (IOException e) {
			e.printStackTrace();
			Display.destroy();
			System.exit(1);
		}
		
		texture.bind();
		
		GL11.glBegin(GL11.GL_QUADS);
		
		GL11.glTexCoord2f(1,1);
		GL11.glVertex2f(000,000);
		GL11.glTexCoord2f(0,1);
		GL11.glVertex2f(200,000);
		GL11.glTexCoord2f(0,0);
		GL11.glVertex2f(200,200);
		GL11.glTexCoord2f(1,0);
		GL11.glVertex2f(000,200);
		
		GL11.glEnd();
	}
}
