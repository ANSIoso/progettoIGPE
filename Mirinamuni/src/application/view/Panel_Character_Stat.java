package application.view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import application.controller.SceneHandler;
import application.model.Character;
import application.model.General_Errors;

public class Panel_Character_Stat extends JPanel {
	
	// classe che definisce la visualizzazione delle informazioni sulle statistiche dei vari characters 
	
	private static final long serialVersionUID = -3430611071966906411L;

	Image stat_icon;

	public Panel_Character_Stat(int character, int type, Font font) {
		JPanel stat_bar = new JPanel();

		this.setLayout(new BorderLayout());

		this.setOpaque(false);
		stat_bar.setOpaque(false);

		stat_icon = loadImage("Characters/Stat_Icons/Stat_icon_" + type + ".png");

		JLabel stat;

		switch (type) {
		case Character.LIFE:
			stat = new JLabel("       LIFE");
			break;
		case Character.DAMAGE:
			stat = new JLabel("     DAMAGE");
			break;
		case Character.DEFENCE:
			stat = new JLabel("     DEFENCE");
			break;
		case Character.SPEED:
			stat = new JLabel("      SPEED");
			break;
		default:
			stat = new JLabel("???:");
		}

		stat.setFont(font.deriveFont(24f));

		int num_icon = Character.getCharactersStats(character)[type] / 10;
		for (int i = 0; i < num_icon; i++) {
			ImageIcon icon = new ImageIcon(stat_icon.getScaledInstance(50, 50, Image.SCALE_FAST));
			JLabel lIcon = new JLabel(icon);
			stat_bar.add(lIcon, BorderLayout.CENTER);
		}

		this.add(stat, BorderLayout.LINE_START);
		this.add(stat_bar, BorderLayout.PAGE_END);
	}

	// caricamento immagine in base alla statistica
	private Image loadImage(String path) {
		try {
			return ImageIO.read(Panel_Character_Stat.class.getClassLoader().getResourceAsStream(path));
		} catch (IOException e) {
			SceneHandler.getInstance().showErrors(General_Errors.LOADUIERROR);
		}

		return null;
	}

}
