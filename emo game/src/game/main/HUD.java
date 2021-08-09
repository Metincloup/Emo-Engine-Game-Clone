package game.main;

import java.awt.*;
import java.awt.image.BufferedImage;

import java.io.IOException;

import javax.imageio.ImageIO;

public class HUD {
	private int nmbr = 0;
	private int score = 0;
	private int fpsi = 0;
	BufferedImage image;

	public void tick() {
		if (nmbr == 0) {
			runonce();
			nmbr++;
		}
	}

	public void runonce() {

		try {
			image = ImageIO.read(getClass().getResource("res/grass.jpg"));
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	public void arttýr() {
		setScore(getScore() + 1);

	}

	public void setfps(int fpsi) {
		this.fpsi = fpsi;

	}

	public void render(Graphics g) {

		g.setColor(Color.black);

		g.drawString("Skorun : " + score, 15, 24);

		g.drawString("FPS : " + fpsi, 15, 420);

	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

}