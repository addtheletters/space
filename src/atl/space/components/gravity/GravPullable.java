package atl.space.components.gravity;

import java.util.List;

import org.lwjgl.util.vector.Vector3f;

import atl.space.components.linearmotion.accel.AccelProvider;
import atl.space.entities.Entity;

public interface GravPullable extends AccelProvider{
  
  public double getGravMass();
  public Vector3f getNetPull(int delta, List<Entity> entities);
  //modifiers will actually modify what getGravMass returns, not factoring applyPull
}
