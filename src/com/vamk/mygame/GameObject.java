package com.vamk.mygame;

import java.awt.Graphics2D;
import java.awt.Rectangle;


/**
 * this will create for all game objects that we may have in our game
 * like enemy, player, ... those objects will be inherited from this class
 * @author KhanhHoang
 *
 */
public abstract class GameObject {
 	
	
	//protected is the same with public or private
	//but it only can be accessed by object that inherits from game object
	protected int x, y;
	protected ID id;
	protected int dx, dy;
	
	/**
	 * Constructor GameObject for our game objects
	 * when we create an instance of our game object we need set 3 parameters
	 * @param x
	 * @param y
	 * @param id
	 */
	public GameObject(int x, int y, ID id) {
		this.x = x;
		this.y = y;
		this.id = id;
	}
	
	public abstract void tick();
		
	
	public abstract void render(Graphics2D g2d);
	
	//this Rectangle class use to handle our rectangle collision 
	public abstract Rectangle getBounds();
	
	
	//geters and seters for our game objects
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	
	public void setId(ID id) {
		this.id = id;
	}
	public ID getId() {
		return id;
	}
	
	public void setDx(int dx) {
		this.dx = dx;
	}
	
	public void setDy(int dy) {
		this.dy = dy;
	}
	
	public int getDx() {
		return dx;
	}
	public int getDy() {
		return dy;
	}
		
}

