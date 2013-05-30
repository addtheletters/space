package atl.space.components;

import java.util.List;

import atl.space.entities.Entity;

public abstract class Component implements Cloneable{
	 
    protected String id;
    protected Entity owner;
    
    public Component(String id) {
    	this.id = id;
    }
    
    public String getId()
    {
        return id;
    }
    
 
    public void setOwnerEntity(Entity owner)
    {
    	this.owner = owner;
    }
    
    public List<String> getPrerequisiteIDs(){
    	return null;
    }
    
    //public Component clone(){
    //	System.err.println("Not implemented");
    //	return null;
    //}
    
    //public abstract SOMEKINDOFDATA getRelevantData();
    
    public abstract Component clone();
    
    public abstract void update(int delta, List<Entity> entities);
    
    public boolean isRenderable(){
    	return false;
    }
}
