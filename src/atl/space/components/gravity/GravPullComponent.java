package atl.space.components.gravity;

public abstract class GravPullComponent implements GravPullable{
  //public double baseGravMass;
  public abstract double getBaseMass(); //Decided that mass be separate from gravity components so mass can be used
                                        //by stuff like acceleration calculators
  
  public GravPullComponent(){
    this(1);
  }
  public GravPullComponent(double mass){
    super("gravpull");
    this.baseGravMass = mass;
  }
  public double getGravMass(){
    return baseGravMass * getMassModifier();
  }
  private abstract double getMassModifier();
  
  public void applyPull(){
    //modify acceleration of component owner
  }
}
