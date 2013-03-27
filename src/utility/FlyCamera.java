package utility;

import org.lwjgl.input.Keyboard;
//import org.lwjgl.input.Mouse;

//Not complete.

public class FlyCamera extends EulerCamera {

	public FlyCamera() {
		super();
	}
	
	public void processKeyboard(float delta, float speed){
		if(Keyboard.isKeyDown(Keyboard.KEY_E)){
			processSlave(delta, speed * 100);
		}
		else{
			processSlave(delta, speed);
		}
	}

	 public void processSlave(float delta, float speed) {
	        if (delta <= 0) {
	            throw new IllegalArgumentException("delta " + delta + " is 0 or is smaller than 0");
	        }

	        boolean keyUp = Keyboard.isKeyDown(Keyboard.KEY_UP) || Keyboard.isKeyDown(Keyboard.KEY_W);
	        boolean keyDown = Keyboard.isKeyDown(Keyboard.KEY_DOWN) || Keyboard.isKeyDown(Keyboard.KEY_S);
	        boolean keyLeft = Keyboard.isKeyDown(Keyboard.KEY_LEFT) || Keyboard.isKeyDown(Keyboard.KEY_A);
	        boolean keyRight = Keyboard.isKeyDown(Keyboard.KEY_RIGHT) || Keyboard.isKeyDown(Keyboard.KEY_D);
	        boolean flyUp = Keyboard.isKeyDown(Keyboard.KEY_SPACE);
	        boolean flyDown = Keyboard.isKeyDown(Keyboard.KEY_LSHIFT);

	        if (keyUp && keyRight && !keyLeft && !keyDown) {
	            moveFromLook(speed * delta * 0.003f, 0, -speed * delta * 0.003f);
	        }
	        if (keyUp && keyLeft && !keyRight && !keyDown) {
	            moveFromLook(-speed * delta * 0.003f, 0, -speed * delta * 0.003f);
	        }
	        if (keyUp && !keyLeft && !keyRight && !keyDown) {
	            moveFromLook(0, 0, -speed * delta * 0.003f);
	        }
	        if (keyDown && keyLeft && !keyRight && !keyUp) {
	            moveFromLook(-speed * delta * 0.003f, 0, speed * delta * 0.003f);
	        }
	        if (keyDown && keyRight && !keyLeft && !keyUp) {
	            moveFromLook(speed * delta * 0.003f, 0, speed * delta * 0.003f);
	        }
	        if (keyDown && !keyUp && !keyLeft && !keyRight) {
	            moveFromLook(0, 0, speed * delta * 0.003f);
	        }
	        if (keyLeft && !keyRight && !keyUp && !keyDown) {
	            moveFromLook(-speed * delta * 0.003f, 0, 0);
	        }
	        if (keyRight && !keyLeft && !keyUp && !keyDown) {
	            moveFromLook(speed * delta * 0.003f, 0, 0);
	        }
	        if (flyUp && !flyDown) {
	            y += speed * delta * 0.003f;
	        }
	        if (flyDown && !flyUp) {
	            y -= speed * delta * 0.003f;
	        }
	    }
	

}
