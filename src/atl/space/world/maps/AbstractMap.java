package atl.space.world.maps;

import java.util.Map;

import org.lwjgl.util.vector.Vector3f;

/*
 * deprecated, likely not going to be used
 */
public abstract class AbstractMap<T>{
  //Not exactly sure how to do this
  //Basically we want it set up to be able to track some sort of data for every point in space.
  //Eg: Heat. You should be able to look at any point in space and determine the heat(for the world), 
                                                                //or guess what the heat is(for an observer)
  Map<Vector3f, T> vitalPoints;
  T[][][] background;
  
  //Constructor based on entities?
  
  public T getValue(Vector3f location){
    return getValue(location.getX(), location.getY(), location.getZ());
  }
  
  
  /*
  * Uses data in background to obtain a value for the coords. Maybe also uses vitalPoints?
  */
  public abstract T getValue(double x, double y, double z);
  
  public void addVitalPoint(Vector3f location, T value){
    vitalPoints.put(location, value);
    adjustBackground();
  }
  
  /*
  * Uses vitalPoints data to alter the background.
  */
  public abstract void adjustBackground(); 
  
  
}
