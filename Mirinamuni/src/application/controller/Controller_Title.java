package application.controller;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import application.Loop;
import application.view.View_Title;

// classe che aggiunge la funzione di "accesso" al gioco
public class Controller_Title {
	private Loop<View_Title> l;
	
	public Controller_Title(View_Title title) { 
		l = new Loop<View_Title>(title);
		l.start();
		
		title.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				super.keyPressed(e);
				
				SceneHandler.getInstance().windowChanger("Menu");
				l.interrupt();
			}
		});
	}
}
