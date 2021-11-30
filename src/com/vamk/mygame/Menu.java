package com.vamk.mygame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import com.vamk.mygame.Game.State;

public class Menu extends MouseAdapter{
	
	private Game game;
	private Handler handler;
	private Display display;
	private Random random = new Random();
	private int fileCounter = 0;
	
	public Menu(Game game, Handler handler, Display display) {
		this.game = game;
		this.handler = handler;
		this.display = display; 
	}
	
	public void mousePressed(MouseEvent e) {
		//when we press it, we're storing X and Y position to mx and my
		int mx = e.getX();
		int my = e.getY();
		if(Game.getGameState() == State.MENU) {
			//This mousechecker for play button
			if(mouseChecker(mx, my, 250, 200, 200, 50)) {
				Game.setGameState(State.GAME);
				handler.addObject(new Player(Game.WIDTH_SCREEN/2 - 32, Game.HEIGHT_SCREEN/2 - 32, ID.PLAYER, handler));
				handler.addObject(new Enemy(random.nextInt(Game.WIDTH_SCREEN), random.nextInt(Game.HEIGHT_SCREEN), ID.ENEMY,handler));
			};
			
			//This mousechecker for help button
			if(mouseChecker(mx, my, 250, 275, 200, 50)) {
				Game.setGameState(State.HELP);
			}
			//This mousechecker for quit button
			if(mouseChecker(mx, my, 250, 350, 200, 50)) {
				System.exit(1);
			}
			
		}
		
		//This mousechecker for back button in help 
		if(Game.getGameState() == State.HELP) {
			if(mouseChecker(mx, my, 250, 350, 200, 50)) {
				Game.setGameState(State.MENU);
				return;
			}
		}
		
		//try again button when end
		if(Game.getGameState() == State.END) {
			if(mouseChecker(mx, my, 250, 350, 200, 50)) {
				Game.setGameState(State.GAME);
				display.setLevel(1);
				display.setScore(0);
				handler.addObject(new Player(Game.WIDTH_SCREEN/2 - 32, Game.HEIGHT_SCREEN/2 - 32, ID.PLAYER, handler));
				handler.addObject(new Enemy(random.nextInt(Game.WIDTH_SCREEN), random.nextInt(Game.HEIGHT_SCREEN), ID.ENEMY,handler));
			}
		}
		
	}
	
	public void mouseReleased(MouseEvent e) {
		
	}
	
	/**
	 * we check if our mouse click within the box or not, eg PLAY box that we have already created.
	 * @param mx
	 * @param my
	 * @param x
	 * @param y
	 * @param WIDTH_SCREEN
	 * @param HEIGHT_SCREEN
	 * @return
	 */
	private boolean mouseChecker(int mx, int my, int x, int y, int WIDTH_SCREEN, int HEIGHT_SCREEN) {
		if(mx > x && mx < x+WIDTH_SCREEN) {
			if(my > y && my < y+HEIGHT_SCREEN) {
				return true;
			}else return false;
		}else return false;
	}
	
	public void tick() {
		
	}
	
	/**
	 * render method to create our graphic for menu
	 * @param g2d
	 */
	public void render(Graphics2D g2d) {
		if(Game.getGameState() == State.MENU) {
			Font font = new Font("arial", 1, 50);
			Font font2 = new Font("arial", 1, 30);
			
			g2d.setFont(font);
			g2d.setColor(Color.white);
			g2d.drawString("LET'S PLAY", 200, 150);
			
			g2d.setFont(font2);
			g2d.drawRect(250, 200, 200, 50);
			g2d.drawString("PLAY", 310, 235);
			
			g2d.drawRect(250, 275, 200, 50);
			g2d.drawString("HELP", 310, 310);
			
			g2d.drawRect(250, 350, 200, 50);
			g2d.drawString("QUIT", 310, 385);
			
			//this create content for our help page
		}else if(Game.getGameState() == State.HELP) {
			Font font = new Font("arial", 1, 50);
			Font font2 = new Font("arial", 1, 30);

			g2d.setFont(font);
			g2d.setColor(Color.white);
			g2d.drawString("HELP", 275, 150);
			
			g2d.setFont(font2);
			
			g2d.drawString("WHAT ARE YOU LOOKING FOR?", 120, 230);
			g2d.drawString("NO HELP AVAILABLE", 190, 270);
			g2d.drawString("BACK AND PLAY", 230, 310);

			
			g2d.setFont(font2);
			g2d.drawRect(250, 350, 200, 50);
			g2d.drawString("BACK", 310, 385);
			
		}else if(Game.getGameState() == State.END) {
			if(fileCounter == 0) {
			game.filescore();
			fileCounter++;
			}
			Font font = new Font("arial", 1, 50);
			Font font2 = new Font("arial", 1, 30);

			g2d.setFont(font);
			g2d.setColor(Color.white);
			g2d.drawString("IT'S OVER", 230, 150);
			
			g2d.setFont(font2);
			
			g2d.drawString("Your score is: " + display.getScore(), 220, 230); //display.getScore()
			g2d.drawString("BACK AND PLAY", 225, 310);

			
			g2d.setFont(font2);
			g2d.drawRect(250, 350, 200, 50);
			g2d.drawString("TRY AGAIN", 265, 385);
			
		}
		
	}
}
