package atl.space.entities;

import java.util.List;

public abstract class ObstructionComponent extends Component {
	public abstract boolean hasCollided(Entity e, int delta, List<Entity> entities);
	public abstract boolean isTouching(Entity e);
}
