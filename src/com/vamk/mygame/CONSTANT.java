package com.vamk.mygame;

import javax.swing.ImageIcon;
import java.awt.Image;
public interface CONSTANT {
	
	public static final int WIDTH_SCREEN = 700;
	public static final int HEIGHT_SCREEN = WIDTH_SCREEN / 12 * 9;
	
	//Image for background, for boss and for normal enemy
	public static final Image BACKGROUND_IMG = new ImageIcon("Image/Background_copy.png").getImage().getScaledInstance(WIDTH_SCREEN, HEIGHT_SCREEN, Image.SCALE_FAST);
	public static final Image BOSS_IMG = new ImageIcon("Image/boss.png").getImage().getScaledInstance(64, 64, Image.SCALE_FAST);
	public static final Image ENEMY_IMG = new ImageIcon("Image/enemy2.png").getImage().getScaledInstance(20, 20, Image.SCALE_FAST);

}
