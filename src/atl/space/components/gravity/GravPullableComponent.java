package atl.space.components.gravity;

import atl.space.components.Component;

public abstract class GravPullableComponent extends Component implements GravPullable{
  //public double baseGravMass;
  public abstract double getBaseGravMass(); //Decided that mass be separate from gravity components so mass can be used
                                        //by stuff like acceleration calculators.
  										//So this will get the base mass from a different component.
  
  public GravPullableComponent(){
    super("gravpull");
  }
  
  public double getGravMass(){
    return getBaseGravMass() * getMassModifier();
  }
  protected abstract double getMassModifier();
  
  public void applyPull(){
    //modify acceleration of component owner
  }
}
