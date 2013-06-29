package atl.space.rpg;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TestTextRPG {

	List<ProgressionStage> lineOfProgression = new LinkedList<ProgressionStage>();
	int progress = 0;
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
	
	private void start(){
		lineOfProgression.get(0).launch();
	}
	
	private void addEventsToLine() {
		ProgressionStage start = new ProgressionStage(
				"Darkness. It is dark. Darker than you have ever experienced before. \n"
						+ "When was before? You don't care. It doesn't matter. All that matters \n"
						+ "is the darkness, pressing in on you... Your eyes, are shut, but you \n"
						+ "can feel the darkness nevertheless. You realize all of a sudden that \n"
						+ "you are not breathing.") {
		};
		start.addChoice(new BlatantTextOption("Open eyes",
				"You open your eyes."));
		start.addChoice(new BlatantTextOption("Keep eyes shut",
				"You continue to keep your eyes shut."));
		lineOfProgression.add(start);
		

	}

	private class ProgressionStage extends BlatantTextFork {
		public ProgressionStage(String universalMessage) {
			super(universalMessage, new ArrayList<Option>());
		}
		
		public void launch(){
			displayUniversalChoiceMessage();
			displayText();
			choose(requestChoice());
			applyUniversalConsequence();
		}
		
		public void choose(int index){
			choices.get(index).applyConsequence();
			decisions[progress] = index;
			applyUniversalConsequence();
		}
		
		public void applyUniversalConsequence() {
			progress++;
			if(progress >= lineOfProgression.size()){
				System.out.println("You journey is over.");
				System.exit(0);
			}
			lineOfProgression.get(progress).launch();
		}
	}

}
