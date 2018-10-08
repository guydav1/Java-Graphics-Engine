package dev.guy;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import dev.guy.screen.Display;

public class Game implements Runnable {
	Display display;
	private Thread thread;
	private BufferStrategy bs;
	private Graphics g;

	private String title;
	public final int WIDTH;
	public final int HEIGHT;

	private boolean isRunning = false;

	public Game(String title, int w, int h) {
		WIDTH = w;
		HEIGHT = h;
		this.title = title;
	}

	public synchronized void start() {
		if (isRunning)
			return;
		isRunning = true;
		thread = new Thread(this);
		thread.start();
	}

	public synchronized void stop() {
		if (!isRunning)
			return;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		isRunning = false;
	}

	private void init() {
		display = new Display(title, WIDTH, HEIGHT);
		
	}
int x = 0;
	private void update() {
x++;
	}

	private void render() {
		bs = display.getCanvas().getBufferStrategy();
		if (bs == null) {
			display.getCanvas().createBufferStrategy(2);
			return;
		}
		g = bs.getDrawGraphics();
		g.clearRect(0, 0, WIDTH, HEIGHT);
		g.drawRect(x, 0, 100, 100);
		
		bs.show();
		g.dispose();
	}

	public void run() {
		init();
		int fps = 60;
		double nanoPerFrame = 1_000_000_000/fps;
		double lastTime = System.nanoTime();
		double currentTime;
		int ticks = 0;
		double timer;
		while (isRunning) {
			currentTime = System.nanoTime();
			timer = currentTime - lastTime;
			if(currentTime - lastTime >= nanoPerFrame) {
				update();
				render();
				ticks++;
				lastTime += nanoPerFrame;
			}
			if(timer >= 1_000_000_000) {
			System.out.println(ticks);
			ticks = 0;
			timer = 0;
			}
			
			
			
		}
	}
}
