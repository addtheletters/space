package atl.space.world.maps;

import org.lwjgl.util.vector.Vector3f;

public abstract class AbstractMap{
  //Not exactly sure how to do this
  //Basically we want it set up to be able to track some sort of data for every point in space.
  //Eg: Heat. You should be able to look at any point in space and determine the heat(for the world), 
                                                                //or guess what the heat is(for an observer)
  Map<Vector3f, Object> vitalPoints;
  Object[][][] background;
  
  public Object getValue(Vector3f location){
    return getValue(location.getX(), location.getY(), location.getZ());
  }
  
  public abstract Object getValue(int x, int y, int z);
  
  public void addVitalPoint(Vector3f location, Object value){
    vitalPoints.put(location, value);
  }
  
  
}
