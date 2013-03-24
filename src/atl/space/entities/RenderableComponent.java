package atl.space.entities;

public abstract class RenderableComponent extends Component {
	public abstract void render();
	
	public boolean isRenderable(){
		return true;
	}
	
}
