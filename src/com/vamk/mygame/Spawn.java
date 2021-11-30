package com.vamk.mygame;

import java.util.Random;

public class Spawn {
	
	private Handler handler;
	private Random r = new Random();
	private Display display;
	
	private int scoreKeep = 0;
	
	
	
	public Spawn(Handler handler, Display display) {
		this.handler = handler;
		this.display = display;
	}
	
	
	/**
	 * Create scorekeep to keep track our level and create new enemy when we reach specific level
	 */
	public void tick() {
		scoreKeep++;
		
		if (scoreKeep >= 150) {
			scoreKeep = 0;
			
			display.setLevel(display.getLevel()+1);
			
			if (display.getLevel() == 2) {
				handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH_SCREEN), r.nextInt(Game.HEIGHT_SCREEN), ID.FASTENEMY, handler));
			}else if(display.getLevel() == 3) {
				handler.addObject(new Enemy(r.nextInt(Game.WIDTH_SCREEN), r.nextInt(Game.HEIGHT_SCREEN), ID.ENEMY, handler));
			}else if(display.getLevel() == 4) {
				handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH_SCREEN), r.nextInt(Game.HEIGHT_SCREEN), ID.SMARTENEMY, handler));
			}else if(display.getLevel() == 5) {
				handler.addObject(new Enemy(r.nextInt(Game.WIDTH_SCREEN), r.nextInt(Game.HEIGHT_SCREEN), ID.ENEMY, handler));
			}else if(display.getLevel() == 6) {
				handler.addObject(new Enemy(r.nextInt(Game.WIDTH_SCREEN), r.nextInt(Game.HEIGHT_SCREEN), ID.ENEMY, handler));
			}else if(display.getLevel() == 7) {
				handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH_SCREEN), r.nextInt(Game.HEIGHT_SCREEN), ID.FASTENEMY, handler));
			}else if(display.getLevel() == 8) {
				handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH_SCREEN), r.nextInt(Game.HEIGHT_SCREEN), ID.FASTENEMY, handler));
			}else if(display.getLevel() == 9) {
				handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH_SCREEN), r.nextInt(Game.HEIGHT_SCREEN), ID.SMARTENEMY, handler));
			}else if(display.getLevel() == 10) {
				handler.clearEnemys();
				handler.addObject(new BossEnemy(Game.WIDTH_SCREEN / 2 - 32, -80, ID.BOSSENEMY, handler));
			}
		}
	}
	
}
