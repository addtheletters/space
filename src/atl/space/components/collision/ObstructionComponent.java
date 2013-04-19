package atl.space.components.collision;

import java.util.List;

import atl.space.components.Component;
import atl.space.entities.Entity;

public abstract class ObstructionComponent extends Component {
	public abstract boolean hasCollided(Entity e, int delta, List<Entity> entities);
	public abstract boolean isTouching(Entity e);
}
