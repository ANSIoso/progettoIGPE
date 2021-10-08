package application.view;

import java.awt.BorderLayout;
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

public class View_Title extends JPanel{
	
	// Classe per la visualizzazione della schermata iniziale
	
	private static final long serialVersionUID = 7380780430568592057L;
	
	private JLabel title;
	private JLabel info;
	private JLabel space;
	
	public View_Title() {
		this.setFocusable(true);
		JPanel center = new JPanel();
		
		this.setLayout(new BorderLayout());
		center.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(200, 0, 100, 0);
		
		this.setOpaque(false);
		center.setOpaque(false);
		
		title = new JLabel(load_image("Title.png", true));
		gbc.gridx = 0;
		gbc.gridy = 0;
		center.add(title, gbc);
		
		info = new JLabel(load_image("Title_Info.png", false));
		gbc.gridx = 0;
		gbc.gridy = 1;
		center.add(info, gbc);
		
		space = new JLabel(load_image("Title_Info_Empty.png", false));
		gbc.gridx = 0;
		gbc.gridy = 2;
		center.add(space, gbc);
		
		this.add(center, BorderLayout.CENTER);
	}
	
	private ImageIcon load_image(String path, boolean scale) {
		try {
			Image i = ImageIO.read(View_Title.class.getClassLoader().getResourceAsStream("Title/" + path));
			
			ImageIcon icon;
			if(scale)
				icon = new ImageIcon(i.getScaledInstance(650, 132, Image.SCALE_SMOOTH));
			else
				icon = new ImageIcon(i);
			
			return icon;
		} catch (IOException e) {
			SceneHandler.getInstance().showErrors(General_Errors.LOADUIERROR);
		}
		return null;
	}
	
	public void update() {
		if(!info.isVisible()) {
			info.setVisible(true);
			space.setVisible(false);
		}else {
			info.setVisible(false);
			space.setVisible(true);
		}
	}
}
