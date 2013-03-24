package atl.space.entities;

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
 
    public abstract void update(int delta);
    
    public boolean isRenderable(){
    	return false;
    }
}