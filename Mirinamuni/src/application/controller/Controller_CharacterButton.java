package application.controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import application.Loop;
import application.view.Button_Character_Choose;
// classe che aggiunge l'animazione del tasto di un personaggio quando gli si passa
// sopra con il mouse
public class Controller_CharacterButton {
	private Loop<Button_Character_Choose> l;
	
	public Controller_CharacterButton(Button_Character_Choose c) {
		
		c.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				super.mouseEntered(e);
				l = new Loop<Button_Character_Choose>(c);
				l.start();
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				super.mouseExited(e);
				
				l.interrupt();
			}
		});
	}

}
