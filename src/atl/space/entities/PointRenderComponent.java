package atl.space.entities;

public class PointRenderComponent extends RenderableComponent {
	public PointRenderComponent(){
		renderer = new PointRenderer(owner.position);
	}
	
	public void update(int delta) {
		renderer.update(owner);
	}

	@Override
	public Component getStepped(int delta) {
		PointRenderComponent prc = new PointRenderComponent();
		prc.update(delta);
		return prc;
	}
}
