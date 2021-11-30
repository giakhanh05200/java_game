package com.vamk.mygame;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.vamk.mygame.Game.State;

public class KeyInput extends KeyAdapter implements CONSTANT{
	//initialize handler 
	private Handler handler;
	
	//this boolean array use to make the movement of the player more smooth 
	//and don't get a little delay when we change the direction of the player
	private boolean[] keyDown = new boolean[4];
	
	Game game;
	
	public KeyInput(Handler handler, Game game) {
		this.handler = handler;
		
		this.game = game;
		//setup our array to false at the beginning
		keyDown[0] = false; 
		keyDown[1] = false;
		keyDown[2] = false; 
		keyDown[3] = false;
	}
	/**
	 * function keypress to control and generate when we press key
	 * and escape our game when we press escape key
	 */
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		for(int i=0; i<handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.PLAYER) {
				//key for player 1 and set our boolean array to true when we press
				if (key==KeyEvent.VK_W) {tempObject.setDy(-5);  keyDown[0] = true; }
				if (key==KeyEvent.VK_S) {tempObject.setDy(5);   keyDown[1] = true; }
				if (key==KeyEvent.VK_A) {tempObject.setDx(-5);  keyDown[2] = true; }
				if (key==KeyEvent.VK_D) {tempObject.setDx(5);   keyDown[3] = true; }
			}
			/*if(tempObject.getId() == ID.PLAYER2) {
				//key for player 2
				if (key==KeyEvent.VK_UP) tempObject.setDy(-5);
				if (key==KeyEvent.VK_DOWN) tempObject.setDy(5);
				if (key==KeyEvent.VK_LEFT) tempObject.setDx(-5);
				if (key==KeyEvent.VK_RIGHT) tempObject.setDx(5);

			}*/
		}
		
		if (key == KeyEvent.VK_P) {
			if(Game.getGameState() == State.GAME) {
				if(Game.isPaused()) Game.setPaused(false);
				else Game.setPaused(true);
			}
		}
		
		if (key == KeyEvent.VK_ESCAPE) System.exit(1);
		
	}
	/**
	 * function keyrelease to control and generate when we release key
	 */
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		
		for(int i=0; i<handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.PLAYER) {
				//key for player 1 and give the array boolean to false when we release the key instead of set it to 0
				if (key==KeyEvent.VK_W) keyDown[0] = false; //tempObject.setDy(0);
				if (key==KeyEvent.VK_S) keyDown[1] = false; //tempObject.setDy(0);
				if (key==KeyEvent.VK_A) keyDown[2] = false; //tempObject.setDx(0);
				if (key==KeyEvent.VK_D) keyDown[3] = false; //tempObject.setDx(0);
			
			/*if(tempObject.getId() == ID.PLAYER2) {
				//key for player 2
				if (key==KeyEvent.VK_UP) tempObject.setDy(0);
				if (key==KeyEvent.VK_DOWN) tempObject.setDy(0);
				if (key==KeyEvent.VK_LEFT) tempObject.setDx(0);
				if (key==KeyEvent.VK_RIGHT) tempObject.setDx(0);

			}*/
				//Vertical movement
				if(!keyDown[0] && !keyDown[1]) tempObject.setDy(0);
				//Horizontal movement
				if(!keyDown[2] && !keyDown[3]) tempObject.setDx(0);
			}
		}
	}
}
