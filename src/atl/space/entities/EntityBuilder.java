package atl.space.entities;

import java.util.List;

import org.lwjgl.util.Color;
import org.lwjgl.util.vector.Vector3f;

import atl.space.components.MovementComponent;
import atl.space.components.accel.AccelComponent;
import atl.space.components.accel.RDAccelComponent;
import atl.space.components.emission.EmissionComponent;
import atl.space.components.emission.FTLauncherComponent;
import atl.space.components.render.EquiTriangleOverlayRenderComponent;
import atl.space.components.render.PointRenderComponent;
import atl.space.components.render.PointTrailRenderComponent;
import atl.space.components.render.SquareOverlayRenderComponent;
import atl.space.components.turn.FacingComponent;
import atl.space.components.turn.RTurningComponent;
import atl.space.components.turn.TTurnControlComponent;
import atl.space.components.turn.TargetingOverlayRenderComponent;
import atl.space.components.turn.TurningComponent;

public class EntityBuilder {
	
	public static Entity point(float x, float y, float z) {
		Entity temp = new Entity("defaultpoint");
		temp.addComponent(new PointRenderComponent());
		temp.position = new Vector3f(x, y, z);
		return temp;
	}
	public static Entity mover(float x, float y, float z, float dx, float dy, float dz) {
		Entity temp = new Entity("defaultmover");
		temp.addComponent(new PointRenderComponent());
		temp.addComponent(new MovementComponent(new Vector3f(dx, dy, dz)));
		temp.position = new Vector3f(x, y, z);
		return temp;
	}
	public static Entity trailer(Vector3f pos, Vector3f speed, int traillength, float trailfade) {
		Entity temp = new Entity("defaultmover");
		temp.addComponent(new PointTrailRenderComponent(traillength, trailfade));
		temp.addComponent(new MovementComponent(new Vector3f(speed)));
		temp.position = new Vector3f(pos);
		return temp;
	}
	public static Entity facer(Vector3f pos, Vector3f dirMoving, Vector3f dirFacing){
		Entity temp = new Entity("defaultfacer");
		temp.addComponent(new FacingComponent(dirFacing));
		temp.addComponent(new MovementComponent(dirMoving));
		temp.position = new Vector3f(pos);
		return temp;
	}
	public static Entity emitter(Vector3f pos, Entity emission){
		Entity temp = new Entity("defaultemitter");
		temp.addComponent(new EmissionComponent(emission));
		temp.position = new Vector3f(pos);
		return temp;
	}
	public static Entity accelerator(Vector3f pos, Vector3f dirMoving, Vector3f dirFacing, Vector3f acceleration){
		Entity temp = new Entity("defaultaccelerator");
		temp.addComponent(new FacingComponent(dirFacing));
		temp.addComponent(new MovementComponent(dirMoving));
		temp.addComponent(new AccelComponent(acceleration));
		temp.position = new Vector3f(pos);
		return temp;
	}
	public static Entity turner(Vector3f pos, Vector3f dirMoving, Vector3f dirFacing, Vector3f turn, float turnrate){
		Entity temp = new Entity("defaultturner");
		temp.addComponent(new FacingComponent(dirFacing));
		temp.addComponent(new MovementComponent(dirMoving));
		temp.addComponent(new RTurningComponent(turn, turnrate));
		temp.position = new Vector3f(pos);
		return temp;
	}
	public static Entity dumbAuto(Vector3f pos, Vector3f dirMoving, Vector3f dirFacing, Vector3f acceleration, Vector3f turn){
		Entity temp = new Entity("dumbAuto");
		temp.addComponent(new FacingComponent(dirFacing));
		temp.addComponent(new MovementComponent(dirMoving));
		temp.addComponent(new AccelComponent(acceleration));
		temp.addComponent(new TurningComponent(turn));
		temp.position = new Vector3f(pos);
		return temp;
	}
	public static Entity smartAuto(Vector3f pos, Vector3f dirMoving, Vector3f dirFacing, Vector3f acceleration, float maxAccelF, float maxAccelB, float maxAccelS, Vector3f turn, float maxturn){
		Entity temp = new Entity("smartAuto");
		temp.addComponent(new FacingComponent(dirFacing));
		temp.addComponent(new MovementComponent(dirMoving));
		temp.addComponent(new RDAccelComponent(acceleration, maxAccelF, maxAccelB, maxAccelS));
		temp.addComponent(new RTurningComponent(turn, maxturn));
		//this will not be here permanently
		//temp.addComponent(new TargetingOverlayRenderComponent(glTest.EntityTest.randomColor(255)));
		temp.addComponent(new TargetingOverlayRenderComponent(glTest.EntityTest.randomColor(255), "res/lena.jpg", "JPG"));
		
		temp.position = new Vector3f(pos);
		return temp;	
	}
	
	public static Entity protagonist(Vector3f pos, Vector3f dirFacing, float maxAccelF, float maxAccelB, float maxAccelS, float maxturn){
		Entity temp = new Entity("protagonist");
		temp.addComponent(new FacingComponent(dirFacing));
		temp.addComponent(new MovementComponent());
		temp.addComponent(new RDAccelComponent(new Vector3f(), maxAccelF, maxAccelB, maxAccelS));
		temp.addComponent(new RTurningComponent(new Vector3f(), maxturn));
		temp.addComponent(new PointTrailRenderComponent(100, 0.005f));
		temp.addComponent(new FTLauncherComponent(missile(.01f, 0f, .01f), dirFacing, 1));
		//this will not be here permanently
		temp.addComponent(new SquareOverlayRenderComponent(new Color(0, 255, 255), 20));
		
		temp.position = new Vector3f(pos);
		return temp;	
	}
	
	public static Entity missile(float maxAccelF, float maxAccelB, float maxturn){
		Entity temp = new Entity("missile");
		temp.addComponent(new FacingComponent());
		temp.addComponent(new MovementComponent());
		RDAccelComponent rdac = new RDAccelComponent(new Vector3f(), maxAccelF, maxAccelB, 0);
		rdac.accelForward = maxAccelF;
		temp.addComponent(rdac);
		temp.addComponent(new RTurningComponent(new Vector3f(), maxturn * 100));
		temp.addComponent(new PointTrailRenderComponent(200, 0.005f));
		TTurnControlComponent ttcc = new TTurnControlComponent();
		//ttcc.hardTurn = true;
		temp.addComponent(ttcc);
		//this will not be here permanently
		temp.addComponent(new EquiTriangleOverlayRenderComponent(new Color(255, 100, 100),10));
		//System.out.println("Set up missile");
		return temp;
	}
	
	public static Entity getNearest(Entity origin, List<Entity> entities){
		if(entities.size() == 1){
			return null;
		}
		Entity nearest = entities.get(0);
		float longestdistance = origin.getDistance(nearest.position);
		//Vector3f temp = new Vector3f();
		for(Entity e : entities){
			float dist = origin.getDistance(e.position);
			if(dist < longestdistance){
				nearest = e;
				longestdistance = dist;
			}
		}
		return nearest;
	}
	
	
}
