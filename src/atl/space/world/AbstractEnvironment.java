package atl.space.world;

import java.util.List;

import org.lwjgl.util.vector.Vector3f;

import atl.space.entities.Entity;


public abstract class AbstractEnvironment{
  private List<Entity> entities; //TODO: Turn this into a map maybe of position to entity so getEntity() is easier?
  //or maybe add a map too.
  public List<Entity> getEntities(){
    return entities;
  }
  public Entity getEntity(Vector3f position){
	 //TODO: Make this work.
	  return null; 
  }
  public void update(int delta){
	  for(Entity e : entities){
		  e.update(delta, entities); //TODO: Change update(int, list<Entity>) to update(int, Environment)
	  }
  }
  
}
