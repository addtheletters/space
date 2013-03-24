package atl.space.entities;

import java.util.List;

public abstract class Component {
	 
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
    
    public abstract void update(int delta, List<Entity> entities);
    
    public abstract Component getStepped(int delta, List<Entity> entities);
    
    public boolean isRenderable(){
    	return false;
    }
}