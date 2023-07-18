package game.main;

import java.awt.*;
import java.awt.image.BufferedImage;

import java.io.IOException;

import javax.imageio.ImageIO;

public class HUD {

	private int score = 0;
	private int fps = 0;
	BufferedImage GrassImage;

	public HUD() {
		try {
			GrassImage = ImageIO.read(getClass().getResource("res/grass.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void tick() {
	}

	public void arttir() {
		setScore(getScore() + 1);
	}

	public void setfps(int fps) {
		this.fps = fps;

	}

	public void render(Graphics g) {
		g.drawImage(GrassImage, 0, 0, 640, 480, null);
		g.setColor(Color.black);
		g.drawString("Skorun : " + score, 15, 24);
		g.drawString("FPS : " + fps, 15, 420);
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

}