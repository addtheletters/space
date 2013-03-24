package atl.space.entities;

public abstract class RenderableComponent extends Component {
	public EntityRenderer renderer;
	
	public boolean isRenderable(){
		return true;
	}
	
	
}
