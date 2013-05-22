package atl.space.components.gravity;

import java.util.List;

import atl.space.entities.Entity;

public interface GravPullable{
  
  public double getGravMass();
  public void applyPull(int delta, List<Entity> entities);
  //modifiers will actually modify what getGravMass returns, not factoring applyPull
}
