package atl.space.components.spawner;

import java.util.List;

import org.lwjgl.util.vector.Vector3f;

import atl.space.components.Component;
import atl.space.components.cargo.CargoComponent;
import atl.space.entities.Entity;
import atl.space.entities.EntityBuilder;
import atl.space.inventory.InventoryException;

public class MissileLauncherTestComponent extends FTLauncherComponent {

	private int tickTime;
	private int launchDelay;
	private static final int DEFAULT_DELAY = 100;

	public List<String> getPrerequisiteIDs() {
		List<String> prids = super.getPrerequisiteIDs();
		prids.add("cargo");
		return prids;
	}

	public MissileLauncherTestComponent(Vector3f dir, float speed, int tickDelay) {
		super(dir, speed);
		launchDelay = tickDelay;
		tickTime = launchDelay;
	}

	public MissileLauncherTestComponent(Vector3f dir, float speed) {
		this(dir, speed, DEFAULT_DELAY);
	}

	public MissileLauncherTestComponent(MissileLauncherTestComponent mltc) {
		super(mltc);
		launchDelay = DEFAULT_DELAY;
		tickTime = launchDelay;
	}

	@Override
	public void trigger(List<Entity> entities) {
		if (canEmit()) {
			super.trigger(entities);
			tickTime = launchDelay;
			weaponLoaded = false;
		}
	}

	@Override
	protected Entity buildEmission() {
		return buildMissile(.01f, 0f, .01f);
	}

	private Entity buildMissile(float maxAccelF, float maxAccelB, float maxturn) {
		return EntityBuilder.missile(maxAccelF, maxAccelB, maxturn);
	}

	@Override
	protected boolean canEmit() {
		return tickTime == 0;
	}

	@Override
	public Component clone() {
		return new MissileLauncherTestComponent(this);
	}

	private boolean ammoOut = false; // nonessential variable so ammoout message
										// displays just once

	private boolean weaponLoaded = false;

	private final String AMMO_ID = "testmissileammo";

	@Override
	public void update(int delta, List<Entity> entities) {
		super.update(delta, entities);
		if (weaponLoaded == false) {
			CargoComponent cc = (CargoComponent) owner.getComponent("cargo");
			try {
				if (cc.containsItem(AMMO_ID)) { // if missile is not loaded and
												// cargo has missile, loads the
												// missile
					cc.removeItem(cc.getItem(AMMO_ID));
					weaponLoaded = true;
				}
			} catch (InventoryException e) {
				if (ammoOut == false) {
					ammoOut = true;
					if (DEBUG) {
						System.out.println(e.getMessage());
						System.out.println("Ammo out!");
					}
				}
			}
		}
		if (weaponLoaded && tickTime > 0)
			tickTime--; // missile is removed from cargo and in the weapon,
						// being prepped for launch
	}

}
