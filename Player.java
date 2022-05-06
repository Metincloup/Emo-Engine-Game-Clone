package game.main;

import java.awt.*;
import java.awt.image.*;
import java.io.*;

import javax.imageio.*;

import game.main.Game.*;

public class Player extends GameObject {

	Handler handler = new Handler();
	BufferedImage PlayerImage;

	KeyInput input;
	Game game;

	public Player(int x, int y, ID id, Handler handler, Game game) {
		super(x, y, id);
		this.handler = handler;
		try {
			PlayerImage = ImageIO.read(getClass().getResource("res/stv.jpg"));

		} catch (IOException e) {

			e.printStackTrace();
		}
		input = game.getKeyInput();
	}

	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 32, 32);
	}

	public void tick() {

		if (input.keyDown[0] == true) {
			this.y -= 3;
		}
		if (input.keyDown[1] == true) {
			this.y += 3;
		}
		if (input.keyDown[2] == true) {
			this.x -= 3;
		}
		if (input.keyDown[3] == true) {
			this.x += 3;
		}
		x = (int) Game.clamp(x, 0, Game.WIDTH - 38);
		y = (int) Game.clamp(y, 0, Game.HEIGHT - 61);

		collision();
	}

	private void collision() {
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			if (tempObject.getId() == ID.Enemy) {
				if (getBounds().intersects(tempObject.getBounds())) {
					Game.gameState = STATE.End;
				}
			}

		}
	}

	public void render(Graphics g) {
		if (!(Game.gameState == Game.STATE.End)) {
		} else if (Game.gameState == Game.STATE.End) {
			g.setColor(Color.black);
			g.drawRect(0, 0, 640, 480);
		}
		g.drawImage(PlayerImage, (int) x, (int) y, 32, 32, null);
	}

}