package atl.space.rpg;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TestTextRPG {

	List<ProgressionStage> lineOfProgression = new LinkedList<ProgressionStage>();
	int next = 0;
	int[] decisions = new int[999];
	int courage = 0;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new TestTextRPG();
	}

	public TestTextRPG() {
		addEventsToLine();
		start();
	}

	private void start() {
		lineOfProgression.get(0).launch();
	}

	private void addEventsToLine() {
		ProgressionStage start = new ProgressionStage(
				"Darkness. It is dark. Darker than you have ever experienced before. \n"
						+ "When was before? You don't care. It doesn't matter. All that matters \n"
						+ "is the darkness, pressing in on you... Your eyes are shut, but you \n"
						+ "can feel the darkness nevertheless. You realize all of a sudden that \n"
						+ "you are not breathing.");
		start.addChoice(new BlatantTextOption("Open eyes",
				"You open your eyes.", 1) {
			public void applyConsequence() {
				courage += 5;
				super.applyConsequence();
			}
		});
		start.addChoice(new BlatantTextOption("Keep eyes shut",
				"You continue to keep your eyes shut.", 2));
		lineOfProgression.add(start);

		ProgressionStage stage1 = new ProgressionStage(
				"You see darkness. (big ******* surprise.) Yet... you can see. \n"
						+ "A shadowy figure, somehow even darker than your surroundings, \n"
						+ "approaches you. It stops several feet in front of you and \n"
						+ "regards you with an emotion you cannot identify. \n"
						+ "You feel compulsed to ask a question.");
		stage1.addChoice(new BlatantTextOption("'Who are you?'",
				"You ask the figure who they are.", 3) {
			public void applyConsequence() {
				courage += 3;
				super.applyConsequence();
			}
		});
		stage1.addChoice(new BlatantTextOption("'Why am I here?'",
				"You demand to know why you are there.", 3) {
			public void applyConsequence() {
				courage += 2;
				super.applyConsequence();
			}
		});
		lineOfProgression.add(stage1);

		ProgressionStage stage2 = new ProgressionStage(
				"You are unable or unwilling to confront the darkness: confront your fears. \n"
						+ "A mysterious voice calls out unintelligibly to you from the darkness, \n"
						+ "and you feel secure within your own mind. But which is it? ");
		stage2.addChoice(new BlatantTextOption("Unable",
				"You found yourself unable to open your eyes.", 3) {
			public void applyConsequence() {
				courage += 2;
				super.applyConsequence();
			}
		});
		stage2.addChoice(new BlatantTextOption(
				"Unwilling",
				"You found yourself unwilling to confront what may lie beyond.",
				3) {
			public void applyConsequence() {
				courage += 1;
				super.applyConsequence();
			}
		});
		lineOfProgression.add(stage2);

		ProgressionStage stage3 = new ProgressionStage(
				"'There's more going on here than you know,' it says at last.") {
			public void displayText() {
				if (decisions[0] == 0) {
					System.out.println("The figure stares impassively.");
				} else {
					System.out
							.println("The mysterious voice's words become clear.");
				}
				if (courage > 5) {
					System.out
							.println("'You have demonstrated that you possess the means to an end. \n"
									+ "But what end? Which end? Only time will tell...'");
				} else {
					System.out
							.println("'You have demonstrated who you are, what you are. \n"
									+ "How you interpret this demonstration is up to you...");
				}
				if (decisions[0] == 0) {
					System.out
							.println("The figure fades away into the darkness, \n"
									+ "and the darkness presses in on you once more...");
				} else {
					System.out
							.println("The voice fades, leaving only echoes... \n"
									+ "Even your eyelids cannot hide the darkness still pressing in on you.");
				}
				super.displayText();
			}
		};
		stage3.addChoice(new BlatantTextOption("Die",
				"You let yourself be taken by the darkness.", 99));
		stage3.addChoice(new BlatantTextOption("Live",
				"You push forth against the darkness.", 4) {
			public void applyConsequence() {
				courage += 3;
				super.applyConsequence();
			}
		});
		lineOfProgression.add(stage3);

		ProgressionStage stage4 = new ProgressionStage(
				"But the darkness fights back.") {
			public void displayText() {
				System.out.println("You courage is " + courage);
				if (decisions[0] == 1) {
					System.out
							.println("How can you hope to fight the darkness if you are");
					if (decisions[2] == 0) {
						System.out.print("unable ");
					} else {
						System.out.print("unwilling ");
					}
					System.out.println("to face it?");
				}
				for (int i = 0; i < choices.size(); i++) {
					if (i < 1 || courage >= 5) {
						System.out.println((i + 1) + ": "
								+ choices.get(i).getDescription());
					}
				}
			}
		};
		stage4.addChoice(new BlatantTextOption("Fade away",
				"You fade into the darkness.", 99));
		stage4.addChoice(new BlatantTextOption("Fight",
				"You fight back against the dark.", 99));
		lineOfProgression.add(stage4);

	}

	private class ProgressionStage extends BlatantTextFork {
		public ProgressionStage(String universalMessage) {
			super(universalMessage, new ArrayList<Option>());
		}

		public void launch() {
			displayUniversalChoiceMessage();
			displayText();
			choose(requestChoice());
			applyUniversalConsequence();
		}

		public void choose(int index) {
			choices.get(index).applyConsequence();
			decisions[next] = index;
			applyUniversalConsequence();
		}

		public void applyUniversalConsequence() {
			next = getNext();
			if (next >= lineOfProgression.size()) {
				System.out.println("You journey is over.");
				System.exit(0);
			}
			lineOfProgression.get(next).launch();
		}

		public int getNext() {
			return ((BlatantTextOption) choices.get(decisions[next]))
					.getNextEventIndex();
		}

	}

}
