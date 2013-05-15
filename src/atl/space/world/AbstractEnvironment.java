package atl.space.world;

import java.util.Collection;
import java.util.List;

import atl.space.entities.Entity;
import atl.space.world.maps.AbstractMap;


public abstract class AbstractEnvironment{
  private List<Entity> entities;
  private Collection<AbstractMap> maps;
  //private HeatMap heat;
  //private GravityMap gravity;
  public List<Entity> getEntities(){
    return entities;
  }

  public Collection<AbstractMap> getMaps(){
    return maps;
  }
}
