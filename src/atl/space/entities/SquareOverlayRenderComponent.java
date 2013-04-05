package atl.space.entities;

import java.nio.FloatBuffer;
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
	public SquareOverlayRenderComponent(FloatBuffer orthoProjMatrix, Color color, float size){
		//TODO make it not need an orthoprojmatrix
		super(orthoProjMatrix);
		this.color = color;
		this.size = size;
	}
	
	
	public void render(){
		setUp2D();
		
		glColor4f(color.getRed()/255.0f, color.getGreen()/255.0f, color.getBlue()/255.0f, color.getAlpha()/255.0f);
		glBegin(GL_LINE_STRIP);
			//TODO make this draw right
		glEnd();
		backTo3D();
	}
	
}
