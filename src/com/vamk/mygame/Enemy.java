package com.vamk.mygame;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Color;

public class Enemy extends GameObject implements CONSTANT{
	
	//private Handler handler;
	
	/**
	 * enemy constructor for our enemy with velocity of x and y 
	 * @param x
	 * @param y
	 * @param id
	 * @param handler
	 */
	public Enemy(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		
		//this.handler = handler;
		
		dx = 5;
		dy = 5;
	}
	
	
	//Rectangle getbound for our enemy
	public Rectangle getBounds() {
		return new Rectangle(x, y ,16, 16);
	}

	
	//Tick to make our enemy that go around our frame like what we see and trail class for our objects as well 
	@Override
	public void tick() {
		x += dx;
		y += dy;
		
		if(y <= 0 || y >= Game.HEIGHT_SCREEN - 32) dy *=-1;
		if(x <= 0 || x >= Game.WIDTH_SCREEN - 16) dx *=-1;
		 
		//handler.addObject(new Trail(x, y, ID.TRAIL, Color.red, 16, 16, 0.05f, handler));
	}

	@Override
	public void render(Graphics2D g2d) {
		g2d.setColor(Color.red);
		//g2d.fillRect(x,y,16,16);
		g2d.drawImage(ENEMY_IMG, x, y, null);
	}

}
