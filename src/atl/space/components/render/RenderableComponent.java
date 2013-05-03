package atl.space.components.render;

import atl.space.components.Component;

public abstract class RenderableComponent extends Component {
	//public EntityRenderer renderer;
	
	public RenderableComponent(String id) {super(id);}
	
	public abstract void render();
	
	public boolean isRenderable(){
		return true;
	}
	
}
