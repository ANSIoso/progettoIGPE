package application.view;

import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import application.controller.SceneHandler;
import application.model.General_Errors;

public class View_Battle_Win extends JPanel{
	
	// Classe che definisce la schermata di vittoria della battaglia
	
	private static final long serialVersionUID = -4035355168002883959L;
	
	private String path = "Battle/Battle_Win_";
	private Image[] images = new Image[2];
	
	private GridBagConstraints gbc = new GridBagConstraints();
	
	public View_Battle_Win(int player, int character) {
		this.setOpaque(false);
		
		this.setLayout(new GridBagLayout());
		gbc.insets = new Insets(40, 0, 0, 0);
		
		loadImages();
		
		Button_Character_Player winner = new Button_Character_Player(player);
		winner.choosePg(character);
		gbc.gridx = 0;
		gbc.gridy = 0;
		this.add(winner, gbc);
		
		ImageIcon img = new ImageIcon(images[1]);
		JLabel continue_ = new JLabel(img); 
		gbc.gridx = 0;
		gbc.gridy = 1;
		this.add(continue_, gbc);
	}
	
	private void loadImages() {
		try {
			images[0] = ImageIO.read(View_Battle_Win.class.getClassLoader().getResourceAsStream(path + "Background.png"));
			images[1] = ImageIO.read(View_Battle_Win.class.getClassLoader().getResourceAsStream(path + "continue.png"));
		} catch (IOException e) {
			SceneHandler.getInstance().showErrors(General_Errors.LOADUIERROR);
		}
	}

	// posizione della schermata
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.drawImage(images[0], this.getSize().width/6, this.getSize().height/5, null);
	}
	
	
}
