package com.vamk.mygame;

import java.awt.Color;
import java.awt.Graphics2D;
import java.io.Serializable;


public class Display implements Serializable, CONSTANT{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//public static int health = 100;
	private static int health = 100;
	private int greenValue = 255; //
	
	private int score = 0;
	private int level = 1;
	
	
	//Generate green when health decrease and score increase
	public void tick() {
		
		setHealth(Game.border(getHealth(), 0, 100)); //
		greenValue = Game.border(greenValue, 0, 255);
		greenValue = getHealth()*2; //
		
		score++;
	}
	
	
	/**
	 * Display our health bar with render
	 * Display score and level 
	 * @param g2d
	 */
	public void render(Graphics2D g2d) {
		g2d.setColor(Color.gray);
		g2d.fillRect(15,15,200,32);
		g2d.setColor(new Color(100, greenValue, 0));
		g2d.fillRect(15,15,getHealth()*2,32); //
		g2d.setColor(Color.white);
		g2d.drawRect(15,15,200,32);
		
		g2d.drawString("Score: " + score, 15, 70);
		g2d.drawString("Level: " + level, 15, 85);

	}
	
	//getters and setters for our score and level
	public void setScore(int score) {
		this.score = score;
	}
	public int getScore() {
		return score;
	}
	
	public void setLevel(int level) {
		this.level = level;
	}
	public int getLevel() {
		return level;
	}


	public static int getHealth() {
		return health;
	}


	public static void setHealth(int health) {
		Display.health = health;
	}


	
	
}
