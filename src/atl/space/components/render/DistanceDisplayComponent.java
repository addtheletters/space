package atl.space.components.render;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.glColor4f;

import java.awt.Font;
import java.nio.FloatBuffer;
import java.text.DecimalFormat;

import org.lwjgl.util.vector.Vector3f;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;

import utility.Camera;
import atl.space.components.Component;

public class DistanceDisplayComponent extends Overlay2DRenderComponent {
	
	private static final boolean DEBUG = false;
	
	private static DecimalFormat formatter = new DecimalFormat("0.00");
	private Camera view;
	private UnicodeFont font;
	
	private static float WIDTH = 640;
	private static float HEIGHT = 480;
	
	public DistanceDisplayComponent(){
		this((Camera)null);
	}
	
	public DistanceDisplayComponent(Camera cam){
		this(cam, new UnicodeFont(new Font("Arial", java.awt.Font.PLAIN,
				10)));
	}
	
	public DistanceDisplayComponent(Camera cam, UnicodeFont font){
		super("distancedisplay");
		setOrthoProjMatrix(createOrthoMatrix(0, WIDTH, HEIGHT, 0, 1, -1));
		//jasgsfkjaghkjfhagkj height is backwards!?!?!?
		this.view = cam;
		if(DEBUG) System.out.println("DEBUG: DistanceDisplayComponent: " + view);
		this.font = font;
		setUpFonts();
	}
	
	@SuppressWarnings("unchecked")
	private void setUpFonts() {
		font.getEffects().add(new ColorEffect(java.awt.Color.WHITE));
		font.addAsciiGlyphs();
		try {
			font.loadGlyphs();
		} catch (SlickException e) {
			e.printStackTrace();
		}

	}
	
	public DistanceDisplayComponent(DistanceDisplayComponent ddc){
		super(ddc);
		this.view = ddc.view;
	}
	
	@Override
	public void render() {
		FloatBuffer winpos = getWinPos();
		float windowX = winpos.get(0);
		float windowY = HEIGHT-winpos.get(1);
		setUp2D();
		if (winpos.get(2) > 0 && winpos.get(2) < 1) {			
			renderDistance(windowX, windowY);	
		}
		backTo3D();
	}
	
	public void renderDistance(float windowX, float windowY) {
		if(DEBUG) System.out.println("DEBUG: "+view);
		Vector3f temp = new Vector3f(view.x(), view.y(), view.z());
		if(DEBUG) System.out.println("Camera pos obtained: " + temp);
		float distance = owner.getDistance(temp);
		if(DEBUG) System.out.println("Distance get successful");
		
		font.drawString(
				windowX,
				windowY,
				formatter.format(distance));
		if(DEBUG) System.out.println("Distance print successful");
		glDisable(GL_TEXTURE_2D);
		//glEnable(GL_TEXTURE_2D);
		
	}
	
	
	@Override
	public Component clone() {
		return new DistanceDisplayComponent(this);
	}


}
