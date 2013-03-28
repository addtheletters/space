package atl.space.entities;

import org.lwjgl.util.vector.Vector3f;

public class EntityBuilder {
	
	public static Entity point(float x, float y, float z) {
		Entity temp = new Entity("defaultpoint");
		temp.addComponent(new PointRenderComponent());
		temp.position = new Vector3f(x, y, z);
		return temp;
	}
	public static Entity mover(float x, float y, float z, float dx, float dy, float dz) {
		Entity temp = new Entity("defaultmover");
		temp.addComponent(new PointRenderComponent()); //PointTrailRenderComponent doesn't work :O
		temp.addComponent(new MovementComponent(new Vector3f(dx, dy, dz)));
		temp.position = new Vector3f(x, y, z);
		return temp;
	}
	public static Entity trailer(Vector3f pos, Vector3f speed, int traillength, float trailfade) {
		Entity temp = new Entity("defaultmover");
		temp.addComponent(new PointTrailRenderComponent(traillength, trailfade)); //PointTrailRenderComponent doesn't work :O
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
	
}
