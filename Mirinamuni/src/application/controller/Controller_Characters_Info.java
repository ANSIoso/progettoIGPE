package application.controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import application.view.Button_Character_Choose;
import application.view.Button_Navigation;
import application.view.View_Character_Info;

// classe che aggiunge le funzioni di interazione con la schermata di info
// su un determinato personaggio 
public class Controller_Characters_Info {
	View_Character_Info m;
	
	public Controller_Characters_Info(View_Character_Info m) {
		this.m = m;
		
		addFunction(this.m.getP1Button());
		addFunction(this.m.getP2Button());
		addFunction(this.m.getP3Button());
		addFunction(this.m.getP4Button());
		
		addBackFunction(m.getBackButton());
	}
	
	// funzione che serve a selezionare il personaggio sul quale si vogliono avere info con un click
	private void addFunction(Button_Character_Choose button) {
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				super.mouseReleased(e);
				
				m.load_info(button.getPG());
				m.validate();
				SoundHandler.getInstance().startEffect("Button_Click");
			}
		});
	}
	
	private void addBackFunction(Button_Navigation button) {
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				super.mouseReleased(e);
				SceneHandler.getInstance().back();
				m.resetInfo();
				SoundHandler.getInstance().startEffect("Button_Click");
			}
		});
	}
}
