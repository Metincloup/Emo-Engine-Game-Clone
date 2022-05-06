package game.main;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Enemy extends GameObject {

	BufferedImage TntImage;

	public Enemy(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		velX = 5;
		velY = 5;
		try {
			TntImage = ImageIO.read(getClass().getResource("res/tnt.png"));
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 32, 32);
	}

	@Override
	public void tick() {
		x += velX;
		y += velY;

		if (y <= 0 || y >= Game.HEIGHT - 48)
			velY *= -1;
		if (x <= 0 || x >= Game.WIDTH - 24)
			velX *= -1;

	}

	@Override
	public void render(Graphics g) {

		g.drawImage(TntImage, (int) x, (int) y, 32, 32, null);
	}

}