package atl.space.components.gravity;

public abstract class GravPullComponent implements GravPullable{
  public double baseGravMass;
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
