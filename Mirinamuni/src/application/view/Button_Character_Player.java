package application.view;

import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;

import application.controller.SceneHandler;
import application.model.General_Errors;

// bottone definito per ogni Player esso conterra il personaggio scelto da esso e permetterà 
// di ri sceglierlo a piacimento
public class Button_Character_Player extends Button_Character{
	private static final long serialVersionUID = -2285552262927721502L;
	
	private int player;
	private Image Character;

	public Button_Character_Player(int player){
		this.player = player;
		
		path = "Characters/G" + player + "_Button.png";
		loadBackground(path);
		
		repaint();
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(Character, 0, -30, dimX, dimY, null);
	}
	
	// metodo che permette di sceliere un personaggio 
	public void choosePg(int pg) {
		setPG(pg);
		
		if(0 < getPG() && getPG() < 4)
			path = "Characters/Character_" + getPG() + "/Idle/Idle_1.png";
		else
			path = "Characters/Character_0/Idle/Idle_1.png";
		
		try {
			Character = ImageIO.read(Button_Character_Choose.class.getClassLoader().getResourceAsStream(path));
		} catch (IOException e) {
			SceneHandler.getInstance().showErrors(General_Errors.LOADUIERROR);
		}
		
		repaint();
	}
	// in caso di scelta sbagliata si può riscegliere
	public void resetChoose() {
		PG = UNCHOSEN;
		Character = null;
		repaint();
	}

	public int getPlayer() {
		return player;
	}
	
}
