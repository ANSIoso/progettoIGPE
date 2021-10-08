package application.controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import application.view.Button_Navigation;
import application.view.View_Menu_General;
// classe che aggiunge le funzioni della schermata del menu
public class Controller_Menu_General {
	
	public Controller_Menu_General(View_Menu_General m) {
		addFunction(m.getPlayButton()      , "Play"      );
		addFunction(m.getCharactersButton(), "Characters");
		addFunction(m.getGameInfoButton()  , "GameInfo"  );
		addCloseFunction(m.getExitButton());
	}
	
	// funzione per spostarsi nell'interfaccia attraverso i bottoni
	private void addFunction(Button_Navigation button, String location) {
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				super.mouseReleased(e);
				SceneHandler.getInstance().windowChanger(location);
				SoundHandler.getInstance().startEffect("Button_Click");
			}
		});
	}
	
	//funzione per chiudere il gioco
	private void addCloseFunction(Button_Navigation button) {
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				super.mouseReleased(e);
				SceneHandler.getInstance().disposeApplication();
				SoundHandler.getInstance().startEffect("Button_Click");
			}
		});
	}
}
