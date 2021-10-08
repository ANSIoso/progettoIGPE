package application.view;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JPanel;

import application.model.Character;

public class View_Character extends JPanel{
	
	// Classe generale della visualizzazione della schermata dei pg su schermo con storia e stats
	
	private static final long serialVersionUID = -3449645703033943286L;
	
	protected GridBagConstraints gbc = new GridBagConstraints();
	Button_Navigation back;
	
	Button_Character_Choose p1;
	Button_Character_Choose p2;
	Button_Character_Choose p3;
	Button_Character_Choose p4;
	
	protected JPanel east;
	protected JPanel west;
	Panel_Character_Information story;
	Panel_Character_Information stat_info;
	
	public View_Character() {
		west = new JPanel();
		gbc.insets = new Insets(10, 20, 0, 0);
		JPanel bottom = new JPanel();
		east = new JPanel();
		
		this.setLayout(new BorderLayout());
		west.setLayout(new GridBagLayout());
		bottom.setLayout(new BorderLayout());
		east.setLayout(new GridBagLayout());
		
		this.setOpaque(false);
		west.setOpaque(false);
		bottom.setOpaque(false);
		east.setOpaque(false);
		
		p1 = new Button_Character_Choose(Character.XX);
		gbc.gridx = 0;
		gbc.gridy = 0;
		west.add(p1, gbc);
		
		p2 = new Button_Character_Choose(Character.XY);
		gbc.gridx = 1;
		gbc.gridy = 0;
		west.add(p2, gbc);
		
		p3 = new Button_Character_Choose(Character.YX);
		gbc.gridx = 0;
		gbc.gridy = 1;
		west.add(p3, gbc);
		
		p4 = new Button_Character_Choose(Character.YY);
		gbc.gridx = 1;
		gbc.gridy = 1;
		west.add(p4, gbc);
		
		back = new Button_Navigation("Freccetta", Button_Navigation.BACK);
		bottom.add(back, BorderLayout.LINE_END);
		
		this.add(east, BorderLayout.LINE_END);
		this.add(west, BorderLayout.LINE_START);
		this.add(bottom, BorderLayout.PAGE_END);
	}
	
	public Button_Character_Choose getP1Button() {
		return p1;
	}

	public Button_Character_Choose getP2Button() {
		return p2;
	}

	public Button_Character_Choose getP3Button() {
		return p3;
	}

	public Button_Character_Choose getP4Button() {
		return p4;
	}

	public Button_Navigation getBackButton() {
		return back;
	}
}
