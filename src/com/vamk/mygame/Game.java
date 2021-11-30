package com.vamk.mygame;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.io.File;  // Import the File class
import java.io.FileWriter;
import java.io.IOException;  // Import the IOException class to handle errors
import java.util.Random;



public class Game extends Canvas implements Runnable, CONSTANT{
	
	private static final long serialVersionUID = 3818601250982848645L;
	//public static final int WIDTH_SCREEN = 700;
	//public static final int HEIGHT_SCREEN = WIDTH_SCREEN / 12 * 9;
	
	
	private Thread thread;
	private boolean running = false;
	
	//public static boolean paused = false;
	private static boolean paused = false;
	private Random random;
	private Handler handler;
	private Display display;
	private Spawn spawn;
	private Menu menu;
	//Create 2 state for our game which are game and menu
	public enum State {
		MENU,
		GAME,
		HELP,
		END;
	}
	
	private static State gameState = State.MENU;
	
	
	//initializing our game and add object to our handler
	//When gameState equal GAME, we have game else we have menu

	public Game() {
		handler = new Handler();
		display = new Display();

		menu = new Menu(this, handler, display);
		this.addKeyListener(new KeyInput(handler, this));
		this.addMouseListener(menu);

		new Window(WIDTH_SCREEN, HEIGHT_SCREEN, "My funny game", this);
		
		spawn = new Spawn(handler, display);
		random = new Random();
		
		if(getGameState() == State.GAME) {
			handler.addObject(new Player(WIDTH_SCREEN/2 - 32, HEIGHT_SCREEN/2 - 32, ID.PLAYER, handler));
			handler.addObject(new Enemy(random.nextInt(WIDTH_SCREEN), random.nextInt(HEIGHT_SCREEN), ID.ENEMY,handler));
		}

	}
	
	/**
	 * start method to initialize the new thread and just basically start the thread
	 */
	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	/**
	 * stop method to stop the thread that already running, and change running to false
	 */
	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	//run method with game loop that I took from the internet
	public void run(){
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				tick();
				delta --;
			}
			if(running)
				render();
			
			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
			}
		}
		stop();
	}
	//Tick method is called every frame
	//This allowed game object to update themselves before being rendered
	//When gameState equal GAME, we tick game else we tick menu
	private void tick() {

		
		if(!isPaused()) {
			if(getGameState() == State.GAME) {
				handler.tick();
				display.tick();
				spawn.tick();

				
				if(Display.getHealth() <= 0) {
					Display.setHealth(100);
					setGameState(State.END);
					handler.clearEnemys();
				}
		}
		}else if(getGameState() == State.MENU || getGameState() == State.END) {
			handler.tick();
			menu.tick();

		}
	}
	
	
	
	//Render to create 3 buffer within our game (recommended over 3 buffer)
	//Set color for our background with Graphics2D to black
	//When gameState equal GAME, we render game else we render menu

	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics2D g2d = (Graphics2D) bs.getDrawGraphics();
		
		g2d.setColor(Color.black);
		//g2d.fillRect(0,0, WIDTH_SCREEN, HEIGHT_SCREEN);
		g2d.drawImage(BACKGROUND_IMG, 0, 0, null);
		handler.render(g2d);
		
		if(isPaused()) {
			g2d.setColor(Color.white);
			g2d.drawString("PAUSE", 500, 50);
		}
		if(!isPaused()) {
			g2d.setColor(Color.white);
			g2d.drawString("PRESS P TO PAUSE", 500, 50);
		}
		
		if(getGameState() == State.GAME) {
			display.render(g2d);
			
		}else if(getGameState() == State.MENU || getGameState() == State.HELP || getGameState() == State.END) {
			menu.render(g2d);
		}
		
		
		g2d.dispose();
		bs.show();
	}
	
	//border method to create limit of our objects in the frame
	public static int border(int var, int min, int max) {
		if(var >= max)
			return var = max;
		else if(var <= min)
			return var = min;
		else
			return var;
		
	}
	
	/**
	 * methode filescore to create file and write to file the latest score
	 */
	public void filescore() {
		if(getGameState() == State.END) {
		    try {
			      File myObj = new File("filename.txt");
			      if (myObj.createNewFile()) {
			        System.out.println("File created: " + myObj.getName());
			      } else {
			        System.out.println("File already exists.");
			      }
			    } catch (IOException e) {
			      System.out.println("An error occurred.");
			      e.printStackTrace();
		    }
		    
			try {
			      FileWriter myWriter = new FileWriter("filename.txt");
			      myWriter.write("Score: " + display.getScore());
			      myWriter.close();
			      System.out.println("Successfully wrote to the file.");
			    } catch (IOException e) {
			      System.out.println("An error occurred.");
			      e.printStackTrace();
			}
		}
	}

	public static boolean isPaused() {
		return paused;
	}

	public static void setPaused(boolean paused) {
		Game.paused = paused;
	}

	public static State getGameState() {
		return gameState;
	}

	public static void setGameState(State gameState) {
		Game.gameState = gameState;
	}
	
	

}
