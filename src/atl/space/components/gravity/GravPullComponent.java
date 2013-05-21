package atl.space.components.gravity;

public abstract class GravPullComponent implements GravPullable{
  //public double baseGravMass;
  public abstract double getBaseGravMass(); //Decided that mass be separate from gravity components so mass can be used
                                        //by stuff like acceleration calculators
  
  public GravPullComponent(){
    super("gravpull");
  }
  
  public double getGravMass(){
    return getBaseGravMass() * getMassModifier();
  }
  private abstract double getMassModifier();
  
  public void applyPull(){
    //modify acceleration of component owner
  }
}
