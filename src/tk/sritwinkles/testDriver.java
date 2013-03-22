package tk.sritwinkles;

public class testDriver {
	public static void main(String[] args) {
		/**
		 * GameState
		 * 	- PlayingState
		 * 	- LoadingState
		 * World - World
		 * 	- CurrentLevel
		 * 		- Tile data/entities whatever
		 * Entity - Nothing
		 * 	- Have a list of Components
		 * 	- Compents are data, so you can have like 
		 * PhysicsComponent for physics data
		 * Systems - Do shit
		 * 	- PhysicsSystem which takes PhysicsComponents and simulates them
		 */
		Entity test = new Entity(1, 1, 1, .3, .2, .5);
		Obstruction testO = new Obstruction(test, 2);
		// AccelProfile testProfile = new AccelProfile(5, 1, .5, .3, 3, 0, 0,
		// new Point(), new Point());
		AccelProfile testProfile = new AccelProfile(5, 1, .5, .3, 3, 0, 0,
				new Point(0, -35, 10), new Point(0, 0, 0));
		// yay for long constructors!

		Automaton testA = new Automaton(testO, new Point(2, 1, 0), testProfile,
				false);
		Obstruction collider = new Obstruction(67, 35, 5, 2);
		for (int i = 0; i < 10; i++) {
			System.out.println("Step " + i);
			System.out.println(testA.toString());
			System.out.println("***");
			System.out.println(collider.toString());
			System.out.println("Is touching: " + collider.isTouching(testA));
			System.out.println("Will collide: " + collider.collide(testA));
			System.out.println("----");

			testA.step();
			collider.step();

		}
	}
}