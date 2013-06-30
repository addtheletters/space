package atl.space.rpg;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TestTextRPG {

	List<ProgressionStage> lineOfProgression = new LinkedList<ProgressionStage>();
	int next = 0;
	int[] decisions = new int[999];

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
				"You open your eyes.", 1));
		start.addChoice(new BlatantTextOption("Keep eyes shut",
				"You continue to keep your eyes shut.", 2));
		lineOfProgression.add(start);

		ProgressionStage stage2 = new ProgressionStage(
				"You see darkness. (big ******* surprise.) Yet... you can see. \n"
						+ "A shadowy figure, somehow even darker than your surroundings, \n"
						+ "approaches you. It stops several feet in front of you and \n"
						+ "regards you with an emotion you cannot identify. \n"
						+ "You feel compulsed to ask a question.");
		stage2.addChoice(new BlatantTextOption("'Who are you?'",
				"You ask the figure who they are.", 4));
		stage2.addChoice(new BlatantTextOption("'Why am I here?'",
				"You demand to know why you are there.", 4));
		lineOfProgression.add(stage2);
		
		ProgressionStage stage2_1 = new ProgressionStage(
				"You are unable or unwilling to confront the darkness: confront your fears. \n"
						+ "A mysterious voice calls out unintelligibly to you from the darkness, \n"
						+ "and you feel secure within your own mind. But which is it? ");
		stage2_1.addChoice(new BlatantTextOption("Unable",
				"You found yourself unable to open your eyes.", 4));
		stage2_1.addChoice(new BlatantTextOption("Unwilling",
				"You found yourself unwilling to confront what may lie beyond.", 4));
		lineOfProgression.add(stage2_1);

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
		
		public int getNext(){
			return ((BlatantTextOption)choices.get(decisions[next])).getNextEventIndex();
		}
		
	}

}
