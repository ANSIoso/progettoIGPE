package application.view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import application.controller.SceneHandler;
import application.model.General_Errors;

public class Button_Character extends JPanel{
	
	// Classe generale di visualizzazione per la creazione di un bottone dove si possono memorizzare i pg
	
	private static final long serialVersionUID = 8739062982165815750L;
	
	public static final int UNCHOSEN = -99;

	protected int PG = UNCHOSEN;
	
	protected int dimX = 250;
	protected int dimY = 150;
	
	protected String path = null;
	protected Image background;
	
	public Button_Character() {
		this.setPreferredSize(new Dimension(dimX, dimY));
		this.setOpaque(false);
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(background, 0, 0, dimX, dimY, null);
	}
	
	public int getPG() {
		return PG;
	}
	
	public void setPG(int pg) {
		PG = pg;
	}
	
	public void loadBackground(String name) {
		try {
			background = ImageIO.read(Button_Character_Choose.class.getClassLoader().getResourceAsStream(path));
		} catch (IOException e) {
			SceneHandler.getInstance().showErrors(General_Errors.LOADUIERROR);
		}
	}
}
