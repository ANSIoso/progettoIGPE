package application.controller;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import application.model.Battle;
import application.view.Button_Navigation;
import application.view.View_Menu_Play;
// classe che aggiunge le funzioni di navigazione verso la battaglia
// e ne setta la modalità
public class Controller_Menu_Play {
	public Controller_Menu_Play(View_Menu_Play p) {
		addFunction(p.getVSIAButton() , "Select_char", Battle.SINGLEPLAYER);
		addFunction(p.getPvPButton()  , "Select_char", Battle.MULTIPLAYER);
		addFunction(p.getTrainButton(), "Select_char", Battle.TRAINING);
		
		addMouseBackFunction(p.getBackButton());
		addKeyBackFunction(p);
	}

	// funzione per spostarsi alla battaglia e selezionare la modalità di gioco
	private void addFunction(Button_Navigation button, String location, byte single) {
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				super.mouseReleased(e);
				
				Battle.setupBattleMode(single);
				SceneHandler.getInstance().windowChanger(location);
				SoundHandler.getInstance().startEffect("Button_Click");
			}
		});
	}

	private void addMouseBackFunction(Button_Navigation button) {
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				super.mouseReleased(e);
				
				SceneHandler.getInstance().back();
				SoundHandler.getInstance().startEffect("Button_Click");
			}
		});
	}
	
	private void addKeyBackFunction(View_Menu_Play p) {
		p.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				super.keyPressed(e);
				
				SceneHandler.getInstance().back();
			}
		});
	}
}
