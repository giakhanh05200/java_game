package com.vamk.mygame;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

public class BossEnemyBullet extends GameObject{
	
	private Handler handler;
	
	//random for our velocity x
	Random r = new Random();
	
	/**
	 * enemy constructor for our enemy with velocity of x and y 
	 * @param x
	 * @param y
	 * @param id
	 * @param handler
	 */
	public BossEnemyBullet(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		
		this.handler = handler;
		
		dx = (r.nextInt(10) - 5);
		dy = 5;
	}
	
	
	//Rectangle getbound for our enemy
	public Rectangle getBounds() {
		return new Rectangle(x, y ,4, 4);
	}

	
	//Tick to make our enemy that go around our frame like what we see and trail class for our objects as well 
	@Override
	public void tick() {
		x += dx;
		y += dy;
		
		//if(y <= 0 || y >= Game.HEIGHT - 32) dy *=-1;
		
		//remove our bullet when it goes out the screen
		if(x <= 0 || x >= Game.WIDTH_SCREEN - 4) dx *=-1;
		if(y >= Game.HEIGHT_SCREEN) handler.removeObject(this);
		 
		handler.addObject(new Trail(x, y, ID.TRAIL, Color.red, 4, 4, 0.05f, handler));
	}

	@Override
	public void render(Graphics2D g2d) {
		g2d.setColor(Color.red);
		g2d.fillRect(x,y,4,4);
	}

}
