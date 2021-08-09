package game.main;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import game.main.Game.STATE;

public class Player extends GameObject {

	Handler handler = new Handler();
	BufferedImage image;
	BufferedImage ima;
	private int nmbr = 0;

	public Player(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;

	}

	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 32, 32);
	}

	public void tick() {
		if (nmbr == 0) {
			runonce();
			nmbr++;
		}
		x += velX;
		y += velY;

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

	public void runonce() {

		try {
			image = ImageIO.read(getClass().getResource("res/stv.jpg"));
			ima = ImageIO.read(getClass().getResource("res/grass.jpg"));
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	public void render(Graphics g) {
		if (!(Game.gameState == Game.STATE.End)) {
			g.drawImage(ima, 0, 0, 640, 480, null);
		} else if (Game.gameState == Game.STATE.End) {
			g.setColor(Color.black);
			g.drawRect(0, 0, 640, 480);
		}
		g.drawImage(image, (int) x, (int) y, 32, 32, null);

	}

}