package atl.space.components.gravity;

import atl.space.components.Component;
import atl.space.components.mass.MassAggregatorComponent;

public abstract class GravPullableComponent extends Component implements GravPullable{
 
  public double getBaseGravMass(){
	  Component massAgg = owner.getComponent("massaggregator");
	  return ((MassAggregatorComponent)massAgg).getAggregateMass();
  }
  //Decided that mass be separate from gravity components so mass can be used
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
