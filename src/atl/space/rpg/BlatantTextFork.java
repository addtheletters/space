package atl.space.rpg;

import java.util.List;
import java.util.Scanner;

public abstract class BlatantTextFork extends Fork {
	private Scanner keyin = new Scanner(System.in);
	private String universalMessage;
	
	public BlatantTextFork(String universalMessage, List<Option> options){
		super(options);
		this.universalMessage = universalMessage;
	}
	
	public int requestChoice(){
		return keyin.nextInt()-1;
	}

	@Override
	public void displayUniversalChoiceMessage() {
		System.out.println(universalMessage);
	}

}
