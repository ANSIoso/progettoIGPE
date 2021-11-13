package application;

import javax.swing.JFrame;

import application.controller.SceneHandler;

public class Main {
	public static void main(String[] args) {
		
		JFrame frame = new JFrame();
		
		SceneHandler.getInstance().init(frame);
		
	}

}
