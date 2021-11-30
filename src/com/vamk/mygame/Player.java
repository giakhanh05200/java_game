package com.vamk.mygame;

import java.awt.*;
import java.awt.Color;

public class Player extends GameObject implements CONSTANT{

	
	private Handler handler;
	/**
	 * Player constructor for our player
	 * @param x
	 * @param y
	 * @param id
	 * @param handler
	 */
	public Player(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
	}
	
	//rectangle getbound for our player
	public Rectangle getBounds() {
		return new Rectangle(x, y ,32, 32);
	}
	
	
	//velocity of x and y, our collision method, and trail for our player 
	@Override
	public void tick() {
		x += dx;
		y += dy;
		
		
		x = Game.border(x, 0, Game.WIDTH_SCREEN - 35);
		y = Game.border(y, 0, Game.HEIGHT_SCREEN -55);
		
		handler.addObject(new Trail(x, y, ID.TRAIL, Color.white, 32, 32, 0.05f, handler));

		collision();
	}
	
	
	/**
	 * collision method to handle when the Enemy intersect with our player
	 */
	private void collision() {
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getId() == ID.ENEMY || tempObject.getId() == ID.FASTENEMY || tempObject.getId() == ID.SMARTENEMY || tempObject.getId() == ID.BOSSENEMY || tempObject.getId() == ID.BOSSENEMYBULLET) {
				if(getBounds().intersects(tempObject.getBounds())) {
					//collision code
					Display.setHealth(Display.getHealth() - 2);
				}
			}
		}
	}
	
	
	//set color for our player
	@Override
	public void render(Graphics2D g2d) {
		g2d.setColor(Color.white);
		g2d.fillRect(x, y, 32, 32);
		//g2d.drawImage(PLAYER_IMG, x, y, null);
		
		
	}

	
}
