package atl.space.components.gravity;

public interface GravPullable{
  
  public double getGravMass();
  public void applyPull();
  public void applyPull(double modifier);
  
}
