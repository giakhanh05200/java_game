package com.vamk.mygame;

import java.awt.Graphics2D;
import java.util.ArrayList;



/**
 * this Handler class will maintain or update
 * and render all objects in our game
 * it will loop through all of objects in our game 
 * @author KhanhHoang
 */
public class Handler {
	
	//The linkedlist here is created to handle all of objects
	//We can use arraylist or hashmap but I'd to use linkedlist
	ArrayList<GameObject> object = new ArrayList<GameObject>();
		
	//Get objects of the entire list by for loop and tick 
	public void tick() {
		for(int i = 0; i < object.size(); i++) {
			GameObject tempObject = object.get(i);
			tempObject.tick();
		}
	}
		
	//Loop through all of game objects to update and it renders all the game object 
	public void render(Graphics2D g2d) {
		for(int i = 0; i < object.size(); i++) {
			GameObject tempObject = object.get(i);
			tempObject.render(g2d);
		}
	}
	
	//Loop through all objects and remove which one is not player ID
	public void clearEnemys() {
		for(int i=0; i < object.size(); i++) {
			GameObject tempObject = object.get(i);
			
			if(tempObject.getId() == ID.PLAYER) {
				object.clear();
				if(Game.getGameState() != Game.State.END) {
					addObject(new Player(tempObject.getX(), tempObject.getY(), ID.PLAYER, this));
				}
			}
			
		}
	}
		
	//function add object to add object to our list
	public void addObject(GameObject object) {
		this.object.add(object);
	}
		
	//function remove object to remove object of our list
	public void removeObject(GameObject object) {
		this.object.remove(object);
	}	
}
