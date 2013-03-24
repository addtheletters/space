package atl.space.entities;

public abstract class RenderableComponent extends Component {
	public Renderer renderer;
	
	public boolean isRenderable(){
		return true;
	}
	
}
