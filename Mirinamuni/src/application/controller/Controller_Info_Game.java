package application.controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import application.view.Button_Navigation;
import application.view.View_Info_Game;
// classe che aggiunge le funzioni basilari alla classe generale view info game
public class Controller_Info_Game {
	public Controller_Info_Game(View_Info_Game i) {
		addBackFunction(i.getBackButton());
	}
	
	private void addBackFunction(Button_Navigation button) {
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				super.mouseReleased(e);
				SceneHandler.getInstance().back();
				SoundHandler.getInstance().startEffect("Button_Click");
			}
		});
	}
}
