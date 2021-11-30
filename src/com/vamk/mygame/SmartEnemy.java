package com.vamk.mygame;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Color;

public class SmartEnemy extends GameObject{
	
	private Handler handler;
	private GameObject player;
	
	
	/**
	 * enemy constructor for our smart enemy with velocity of x and y 
	 * @param x
	 * @param y
	 * @param id
	 * @param handler
	 */
	public SmartEnemy(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		
		//loop through and find the object player and set it to player 
		for(int i = 0; i< handler.object.size(); i++) {
			if(handler.object.get(i).getId() == ID.PLAYER) player = handler.object.get(i);
		}
		
		this.handler = handler;
		
		dx = 5;
		dy = 5;
	}
	
	
	//Rectangle getbound for our enemy
	public Rectangle getBounds() {
		return new Rectangle(x, y ,16, 16);
	}

	
	//Tick to make our smart enemy that go around our frame like what we see and trail class for our objects as well 
	@Override
	public void tick() {
		x += dx;
		y += dy;
		
		//diff x and diff y to calculate the dítacne between enemy and player 
		float diffX = x - player.getX() - 16;
		float diffY = y - player.getY() - 16;
		//basic formula to calculate distance between 2 points distance^2 = (x-x0)^2 + (y-y0)^2
		float distance = (float) Math.sqrt((x-player.getX()) * (x-player.getX()) + (y-player.getY()) * (y-player.getY()));
		
		//calculate x and y velocity to hit the target on flat 2d
		dx = (int) Math.round((-1.0/distance)*diffX);
		dy = (int) Math.round((-1.0/distance)*diffY);

		
		if(y <= 0 || y >= Game.HEIGHT_SCREEN - 32) dy *=-1;
		if(x <= 0 || x >= Game.WIDTH_SCREEN - 16) dx *=-1;
		 
		handler.addObject(new Trail(x, y, ID.TRAIL, Color.CYAN, 16, 16, 0.05f, handler));
	}

	@Override
	public void render(Graphics2D g2d) {
		g2d.setColor(Color.CYAN);
		g2d.fillRect(x,y,16,16);
	}

}
