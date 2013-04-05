package atl.space.entities;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.util.Color;


public class SquareOverlayRenderComponent extends Overlay2DRenderComponent {
	private Color color;
	private float size;
	public SquareOverlayRenderComponent(){
		super();
		color = new Color();
		size = 10;
	}
	public SquareOverlayRenderComponent(Color color, float size){
		//TODO make the orthomatrix work right with how render works
		//Figure out the best way of doing these XD
		super();
		setOrthoProjMatrix(createOrthoMatrix(-1, 1, -1, 1, 1, -1));
		this.color = color;
		this.size = size;
	}
	
	
	public void render(){
		setUp2D();
		
		glColor4f(color.getRed()/255.0f, color.getGreen()/255.0f, color.getBlue()/255.0f, color.getAlpha()/255.0f);
		glBegin(GL_LINE_STRIP);
		//TODO make this draw right
		//translatedx = screen representation of owner.position.x
		//translatedy = screen representation of owner.position.y
		//glVertex2f(translatedx - size/2, translatedy - size/2);
		//glVertex2f(translatedx - size/2, translatedy + size/2);
		//glVertex2f(translatedx + size/2, translatedy + size/2);
		//glVertex2f(translatedx + size/2, translatedy - size/2);
		//glVertex2f(translatedx - size/2, translatedy - size/2);
		glEnd();
		backTo3D();
	}
	
}
