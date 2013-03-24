package atl.space.entities;

public abstract class ObstructionComponent extends Component {
	public abstract boolean willCollide(Entity e);
	public abstract boolean isTouching(Entity e);
}
