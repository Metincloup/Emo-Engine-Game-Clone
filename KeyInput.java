package game.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {

	public boolean[] keyDown = new boolean[4];

	public KeyInput() {

		keyDown[0] = false;
		keyDown[1] = false;
		keyDown[2] = false;
		keyDown[3] = false;
	}

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

		if (key == KeyEvent.VK_W) {
			keyDown[0] = true;
		}
		if (key == KeyEvent.VK_S) {
			keyDown[1] = true;
		}
		if (key == KeyEvent.VK_A) {
			keyDown[2] = true;
		}
		if (key == KeyEvent.VK_D) {
			keyDown[3] = true;
		}

		if (key == KeyEvent.VK_ESCAPE)
			System.exit(1);
	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();

		if (key == KeyEvent.VK_W)
			keyDown[0] = false;
		if (key == KeyEvent.VK_S)
			keyDown[1] = false;
		if (key == KeyEvent.VK_A)
			keyDown[2] = false;
		if (key == KeyEvent.VK_D)
			keyDown[3] = false;
	}

}