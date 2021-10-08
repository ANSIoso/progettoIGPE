package application.view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import application.controller.SceneHandler;
import application.model.General_Errors;

public class Panel_Character_Information extends JPanel{
	
	// classe che crea un contenitore per mettere le informazioni su un determinato pg
	
	private static final long serialVersionUID = -4212529526772080862L;
	
	private int dimX = 260;
	private int dimY = 650;
	
	private Image background;

	public Panel_Character_Information(){	
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		this.setPreferredSize(new Dimension(dimX, dimY));
		
		this.setOpaque(false);
		
		String path = "Characters/Info_Characters_Background.png";
		try {
			background = ImageIO.read(Button_Character_Choose.class.getClassLoader().getResourceAsStream(path));
		} catch (IOException e) {
			SceneHandler.getInstance().showErrors(General_Errors.LOADUIERROR);
		}
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(background, 0, 0, dimX, dimY, null);
	}
	// svuota le informazioni caricate, utilizzato se s idevono caricare altre informazioni
	public void reset() {
		this.removeAll();
	}
}
