package atl.space.entities;

import java.util.List;

public abstract class Component implements Cloneable{
	 
    protected String id;
    protected Entity owner;
    
    
    public String getId()
    {
        return id;
    }
    
 
    public void setOwnerEntity(Entity owner)
    {
    	this.owner = owner;
    }
    
    public Component clone(){
    	System.err.println("Not implemented");
    	return null;
    }
    
    public abstract void update(int delta, List<Entity> entities);
    
    public abstract Component getStepped(int delta, List<Entity> entities);
    
    public boolean isRenderable(){
    	return false;
    }
}