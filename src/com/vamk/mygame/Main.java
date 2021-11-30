package com.vamk.mygame;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		new Game();

		Display display = new Display();
		/**
		 * Serialization
		 */
		FileOutputStream fileOutputStream;
		ObjectOutputStream objectOutputStream;
		
		try {
			fileOutputStream = new FileOutputStream("filename2.txt");
			objectOutputStream = new ObjectOutputStream(fileOutputStream);
			
			objectOutputStream.writeObject(display);
		    
		    objectOutputStream.flush();
		    objectOutputStream.close();
		    
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		/* Read from file */
		
		FileInputStream fileInputStream;
		ObjectInputStream objectInputStream;
		
		try {
			fileInputStream = new FileInputStream("filename2.txt");
			objectInputStream = new ObjectInputStream(fileInputStream);
			
			Display loaded = (Display) objectInputStream.readObject();
			objectInputStream.close(); 
			
			System.out.println(loaded.getScore());
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

	}

	
}
