package com.vamk.mygame;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

public class BossEnemy extends GameObject implements CONSTANT{
	
	private Handler handler;
	
	//timer1 and timer2 to help move the bossenemy down and horizontal after. 
	private int timer = 75;
	private int timer2 = 50;
	Random r = new Random();
	
	/**
	 * enemy constructor for our enemy with velocity of x and y 
	 * @param x
	 * @param y
	 * @param id
	 * @param handler
	 */
	public BossEnemy(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		
		this.handler = handler;
		
		dx = 0;
		dy = 2;
	}
	
	
	//Rectangle getbound for our enemy
	public Rectangle getBounds() {
		return new Rectangle(x, y ,64, 64);
	}

	
	//Tick to make our enemy that go around our frame like what we see and trail class for our objects as well 
	@Override
	public void tick() {
		x += dx;
		y += dy;
		
		if(timer<=0) dy = 0;
		else timer --;
		
		if(timer  <= 0) timer2--;
		if(timer2 <= 0) 
		{
			//stop the boss enemy at the top and move horizontally
			if(dx==0) dx = 5;			
			
			//we can change the velocity of our boss but we have to change type int to float
			/*if(dx > 0)
			dx += 0.01;
			else if(dx<0)
			dx -= 0.01;*/
			
			//create spawn of bullet
			int spawn = r.nextInt(5);
			if(spawn == 0) handler.addObject(new BossEnemyBullet(x+32, y+32, ID.BOSSENEMYBULLET, handler));
		}
		
		//if(y <= 0 || y >= Game.HEIGTH_SCREEN - 32) dy *=-1;
		if(x <= 0 || x >= Game.WIDTH_SCREEN - 64) dx *=-1;
		 
		//handler.addObject(new Trail(x, y, ID.TRAIL, Color.red, 64, 64, 0.05f, handler));
	}

	@Override
	public void render(Graphics2D g2d) {
		g2d.setColor(Color.red);
		//g2d.fillRect(x,y,64,64);
		g2d.drawImage(BOSS_IMG, x, y, null);
	}

}
