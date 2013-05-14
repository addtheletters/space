package atl.space.world;

import atl.space.entities.Entity;

public abstract class AbstractEnvironment{
  private List<Entity> entities;
  private HeatMap heat;
  private GravityMap gravity;
  public List<Entity> getEntities(){
    return entities;
  }
  public HeatMap getHeatMap(){
    return heat;
  }
  public GravityMap getGravMap(){
    return gravity;
  }
}
