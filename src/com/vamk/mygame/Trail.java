package com.vamk.mygame;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;


public class Trail extends GameObject implements CONSTANT{

	private float alpha = 1;
	private Handler handler;
	private Color color;
	private int width, height;
	private float life;
	
	
	//life is value between 0.0001 - 0.1
	
	/**
	 * Trail constructor for our object that need trail eg player, enemy, etc
	 * @param x
	 * @param y
	 * @param id
	 * @param color
	 * @param width
	 * @param height
	 * @param life
	 * @param handler
	 */
	public Trail(int x, int y, ID id, Color color, int width, int height,float life, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		this.color = color;
		this.width = width;
		this.height = height;
		this.life = life;
	}
	
	//Tick and render method for our trail class which define the long of trail
	//depends on our life variable and alpha. 
	@Override
	public void tick() {
		if (alpha > life) {
			alpha -= (life - 0.0001f);
		}else handler.removeObject(this);
	}

	@Override
	public void render(Graphics2D g2d) {
		g2d.setComposite(makeTransparent(alpha));
		
		g2d.setColor(color);
		g2d.fillRect(x, y, width, height);
		g2d.setComposite(makeTransparent(1));
	}
	
	
	/**
	 * this method to render out these Transparencies in objects
	 * @param alpha
	 * @return
	 */
	private AlphaComposite makeTransparent (float alpha) {
		int type = AlphaComposite.SRC_OVER;
		return (AlphaComposite.getInstance(type, alpha));
		
	}


	@Override
	public Rectangle getBounds() {
		return null;
	}

}
