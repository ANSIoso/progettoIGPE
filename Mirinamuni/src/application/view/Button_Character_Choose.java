package application.view;

import java.awt.Graphics;
import application.controller.Controller_CharacterButton;

// classe che definisce un bottone utilizzabile per la selezione di un determinato personaggio
// il personaggio allinterno del pulsante può essere animato 
public class Button_Character_Choose extends Button_Character{
	private static final long serialVersionUID = -2900270028660854199L;
	
	private Frame_List frames;
	
	public Button_Character_Choose(int PG) {
		setPG(PG);
		
		path = "Characters/Character_Button.png";
		loadBackground(path);
		
		path = "Characters/Character_" + PG + "/";
		this.frames = new Frame_List(Frame_Handler.getIstance().getResources("Idle", path, false), true);
		
		new Controller_CharacterButton(this);
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(frames.getCurrentFrame(), 0, -30, dimX, dimY, null);
	}
	
	// metodo per aggiornare il "contenuto" del bottone 
	public void update() {
		frames.update();
		repaint();
	}
}
