package application;

import javax.swing.JFrame;

import application.controller.SceneHandler;

public class Main {
	public static void main(String[] args) {
		
		System.out.println("ciao");
		
		JFrame frame = new JFrame();
		
		SceneHandler.getInstance().init(frame);
		
	}

}
