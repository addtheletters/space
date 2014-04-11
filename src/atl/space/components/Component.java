package atl.space.components;

import java.util.List;

import atl.space.aspects.AspType;
import atl.space.entities.Entity;
import atl.space.world.Scene;

public abstract class Component implements Cloneable{
	 
	protected AspType[] reqAspects;
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
    
    public abstract Component clone();
    
    public void update(int delta, Scene sce){
    	// a lot will do nothing
    }
    
    public boolean isRenderable(){
    	return false;
    }

}
