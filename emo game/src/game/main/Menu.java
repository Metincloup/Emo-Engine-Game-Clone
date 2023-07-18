package game.main;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public class Menu extends MouseAdapter {

	private Handler handler;
	private HUD hud;
	int ran;
	Random ra = new Random();
	BufferedImage GrassImage;
	Game game;

	public Menu(Game game, Handler handler, HUD hud) {
		this.handler = handler;
		this.hud = hud;
		this.game = game;

		try {
			GrassImage = ImageIO.read(getClass().getResource("res/grass.jpg"));
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	public void createEnemy() {

		int ran = (int) (Math.random() * 12) + 1;

		System.out.println(ran);
		if (ran == 1) {
			handler.addObject(new Enemy(20, 50, ID.Enemy, handler));
		}
		if (ran == 2) {
			handler.addObject(new Enemy(100, 50, ID.Enemy, handler));
		}
		if (ran == 3) {
			handler.addObject(new Enemy(200, 50, ID.Enemy, handler));
		}
		if (ran == 4) {
			handler.addObject(new Enemy(400, 50, ID.Enemy, handler));
		}
		if (ran == 5) {
			handler.addObject(new Enemy(20, 100, ID.Enemy, handler));
		}
		if (ran == 6) {
			handler.addObject(new Enemy(100, 100, ID.Enemy, handler));
		}
		if (ran == 7) {
			handler.addObject(new Enemy(200, 100, ID.Enemy, handler));
		}
		if (ran == 8) {
			handler.addObject(new Enemy(400, 100, ID.Enemy, handler));
		}
		if (ran == 9) {
			handler.addObject(new Enemy(20, 200, ID.Enemy, handler));
		}
		if (ran == 10) {
			handler.addObject(new Enemy(100, 200, ID.Enemy, handler));
		}
		if (ran == 11) {
			handler.addObject(new Enemy(200, 200, ID.Enemy, handler));
		}
		if (ran == 12) {
			handler.addObject(new Enemy(400, 200, ID.Enemy, handler));
		}
	}

	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();

		if (Game.gameState == Game.STATE.Menu) {
			// play button
			if (mouseOver(mx, my, 210, 150, 200, 64)) {
				Game.gameState = Game.STATE.Game;
				handler.addObject(new Player(Game.WIDTH / 2 - 32, Game.HEIGHT / 2 - 32, ID.Player, handler, game));

				handler.addObject(new Enemy(500, Game.HEIGHT / 2 - 32, ID.Enemy, handler));
			}

			// help button
			if (mouseOver(mx, my, 210, 250, 200, 64)) {
				Game.gameState = Game.STATE.Help;
			}

			// quit button
			if (mouseOver(mx, my, 210, 350, 200, 64)) {
				System.exit(1);
			}
		}

		// back button 4 help
		if (Game.gameState == Game.STATE.Help) {
			if (mouseOver(mx, my, 210, 350, 200, 64)) {
				Game.gameState = Game.STATE.Menu;
				return;
			}
		}
		if (Game.gameState == Game.STATE.End) {
			if (mouseOver(mx, my, 210, 350, 200, 64)) {
				Game.gameState = Game.STATE.Game;
				hud.setScore(0);
				handler.clearEnemies();
				handler.addObject(new Player(Game.WIDTH / 2 - 32, Game.HEIGHT / 2 - 32, ID.Player, handler, game));
			}
		}
	}

	public void mouseReleased(MouseEvent e) {

	}

	private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
		if (mx > x && mx < x + width) {
			if (my > y && my < y + height) {
				return true;
			} else
				return false;
		} else
			return false;

	}

	public void tick() {

	}

	Color startColor = new Color(0, 130, 255);
	Color endColor = new Color(0, 255, 255);
	int startX = 80, startY = -300, endX = 800, endY = 100;

	public void render(Graphics g) {
		if (Game.gameState == Game.STATE.Menu) {

			Font fnt = new Font("arial", 1, 50);
			Font fnt2 = new Font("arial", 1, 30);
			Graphics2D g2d = (Graphics2D) g;

			g.setColor(Color.black);
			g.fillRect(210, 150, 200, 64);
			g.fillRect(210, 250, 200, 64);
			g.fillRect(210, 350, 200, 64);

			g.setFont(fnt);
			g.setColor(Color.white);
			GradientPaint gradient = new GradientPaint(startX, startY, startColor, endX, endY, endColor);
			g2d.setPaint(gradient);
			g2d.drawString("Emo Game", 180, 70);

			g.setFont(fnt2);
			g.drawString("Basla", 270, 190);
			g.drawRect(210, 150, 200, 64);

			g.drawString("Yardim", 267, 290);
			g.drawRect(210, 250, 200, 64);

			g.drawString("cikis", 270, 390);
			g.drawRect(210, 350, 200, 64);

		} else if (Game.gameState == Game.STATE.Help) {
			Font fnt = new Font("arial", 1, 50);
			Font fnt2 = new Font("arial", 1, 30);
			Font fnt3 = new Font("arial", 1, 20);
			Graphics2D g2d = (Graphics2D) g;
			GradientPaint gradient = new GradientPaint(startX, startY, startColor, endX, endY, endColor);

			g.setFont(fnt);

			g.setColor(Color.white);
			g2d.setPaint(gradient);
			g.drawString("Nasil Oynanir", 140, 70);

			g.setFont(fnt3);
			g.drawString("A S D W tuslarini kullanarak tntlerden kac her 3 saniyede", 50, 150);
			g.drawString("bir yeni tntler olusur tntlere degidiginde kaybedersin.", 50, 180);

			g.drawString("Oyun Yapimcisi: METIN DURMUS", 50, 270);
			g.drawString("Emolingo icin yapilmistir.", 50, 310);

			g.setColor(Color.black);
			g.fillRect(210, 350, 200, 64);

			g.setColor(Color.white);
			g.setFont(fnt2);
			g2d.setPaint(gradient);
			g.drawString("Men�ye d�n", 225, 390);
			g.drawRect(210, 350, 200, 64);
		} else if (Game.gameState == Game.STATE.End) {
			Font fnt = new Font("arial", 1, 50);
			Font fnt2 = new Font("arial", 1, 30);
			Font fnt3 = new Font("arial", 1, 20);
			Graphics2D g2d = (Graphics2D) g;
			GradientPaint gradient = new GradientPaint(startX, startY, startColor, endX, endY, endColor);
			g2d.setPaint(gradient);
			g2d.setFont(fnt);

			g.drawString("Oyun Bitti", 190, 70);

			g.setFont(fnt3);
			g.drawString("Skorun: " + hud.getScore(), 260, 150);

			g.setColor(Color.black);
			g.fillRect(210, 350, 200, 64);

			g2d.setPaint(gradient);
			g.setFont(fnt2);
			g.drawString("Yeniden Oyna", 210, 390);
			g.drawRect(210, 350, 200, 64);
		}
		if (Game.gameState == Game.STATE.Game) {
			g.drawImage(GrassImage, 0, 0, 640, 480, null);

		}
	}
}