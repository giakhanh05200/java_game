package com.vamk.mygame;

import java.awt.Canvas;
import java.awt.Dimension;
import javax.swing.JFrame;


public class Window extends Canvas{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8293436208732411365L;
	
	/**
	 * Constructor Window to create our frame and setup dimension, create close operations, 
	 * not resizable, add game class to our frame, and start the game with start method
	 * @param width
	 * @param height
	 * @param title
	 * @param game
	 */
	public Window(int width, int height, String title, Game game) {
		JFrame frame = new JFrame(title);
		
		frame.setPreferredSize(new Dimension(width, height));
		frame.setMaximumSize(new Dimension(width, height));
		frame.setMinimumSize(new Dimension(width, height));
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.add(game);
		frame.setVisible(true);
		game.start();
	}
}
