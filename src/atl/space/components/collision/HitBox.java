package atl.space.components.collision;

import org.lwjgl.util.vector.Vector3f;

public interface HitBox {
	public boolean isInHitbox(Vector3f location);
}
