package game.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game extends Canvas {

	private static final long serialVersionUID = 1L;

	public static final int WIDTH = 640, HEIGHT = WIDTH / 12 * 9;

	private Thread thread;
	private boolean running = false;

	private Handler handler;
	private HUD hud = new HUD();
	Random rand = new Random();
	int ra = rand.nextInt(12);
	private int sayac = 0;
	private Menu menu;
	private KeyInput input = new KeyInput();

	public enum STATE {
		Menu, Help, Game, End
	}

	public static STATE gameState = STATE.Menu;

	public Game() {
		handler = new Handler();
		hud = new HUD();
		menu = new Menu(this, handler, hud);
		new Random();
		this.addKeyListener(input);
		this.addMouseListener(menu);

		if (gameState == STATE.Game) {
			handler.addObject(new Player(WIDTH / 2 - 32, HEIGHT / 2 - 32, ID.Player, handler, this));
			handler.addObject(new Enemy(WIDTH / 2 - 32, HEIGHT / 2 - 32, ID.Enemy, handler));
		}

		new Window(WIDTH, HEIGHT, "Emo Game", this);
	}

	public synchronized void start() {
		thread = new Thread(String.valueOf(this));
		thread.start();
		running = true;
	}

	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void run() {
		ra = rand.nextInt(12);
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				tick();
				delta--;
			}
			if (running)
				render();
			frames++;
			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				hud.setfps(frames);
				frames = 0;
				if (gameState == Game.STATE.Game) {
					hud.arttýr();
					sayac++;
					if (sayac == 3) {
						menu.createEnemy();
						sayac = 0;

					}

				}
			}
		}
		stop();
	}

	private void tick() {

		handler.tick();
		if (gameState == STATE.Game) {
			hud.tick();

		} else if (gameState == STATE.Menu || gameState == STATE.End) {
			menu.tick();
		}
	}

	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}

		Graphics g = bs.getDrawGraphics();

		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);

		if (gameState == STATE.Game) {
			hud.render(g);

		} else if (gameState == STATE.Menu || gameState == STATE.Help || gameState == STATE.End) {
			menu.render(g);

		}
		handler.render(g);
		g.dispose();
		bs.show();
	}

	public static float clamp(float var, float min, float max) {
		if (var >= max)
			return var = max;
		else if (var <= min)
			return var = min;
		else
			return var;
	}

	public KeyInput getKeyInput() {
		return input;
	}

	public static void main(String[] args) {
		new Game();
	}

}