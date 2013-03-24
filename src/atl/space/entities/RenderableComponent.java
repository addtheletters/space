package atl.space.entities;

public abstract class RenderableComponent extends Component {
	protected Renderer renderer;
	
	public boolean isRenderable(){
		return true;
	}
	
}
