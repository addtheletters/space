package atl.space.components.gravity;

public interface GravPullable{
  
  public double getGravMass();
  public void applyPull();
  //modifiers will actually modify what getGravMass returns, not factoring applyPull
}
