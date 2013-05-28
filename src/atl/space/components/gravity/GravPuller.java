package atl.space.components.gravity;

import atl.space.entities.Entity;

public interface GravPuller{
  
  public double getPullForce();
  public boolean hasWithinPullableArea(Entity target);
  
}
