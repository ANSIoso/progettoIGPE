package application.view;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JPanel;

public class View_Menu_Play extends JPanel{
	
	// Classe per la scelta della modalità di gioco
	
	private static final long serialVersionUID = 6408262773035007307L;
	
	private GridBagConstraints gbc = new GridBagConstraints();
	private Button_Navigation VSIA;
	private Button_Navigation PvP;
	private Button_Navigation Train;
	private Button_Navigation back;
	
	
	// Settaggi per i tasti 
	public View_Menu_Play(){
		JPanel gbcp = new JPanel();
		JPanel bottom = new JPanel();
		
		this.setLayout(new BorderLayout());
		gbcp.setLayout(new GridBagLayout());
		bottom.setLayout(new BorderLayout());
		
		this.setOpaque(false);
		gbcp  .setOpaque(false);
		bottom.setOpaque(false);
		
		gbc.insets = new Insets(5, 5, 5, 5);
		
		VSIA = new Button_Navigation("VSIA", Button_Navigation.SQUARE);
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbcp.add(VSIA, gbc);
		
		PvP = new Button_Navigation("PvP", Button_Navigation.SQUARE);
		gbc.gridx = 1;
		gbc.gridy = 3;
		gbcp.add(PvP, gbc);
		
		Train = new Button_Navigation("Train", Button_Navigation.SQUARE);
		gbc.gridx = 2;
		gbc.gridy = 3;
		gbcp.add(Train, gbc);
		
		back = new Button_Navigation("Freccetta", Button_Navigation.BACK);
		bottom.add(back, BorderLayout.LINE_END);
		
		this.add(gbcp, BorderLayout.CENTER);
		this.add(bottom, BorderLayout.PAGE_END);
	}
	
	public Button_Navigation getVSIAButton() {
		return VSIA;
	}
	public Button_Navigation getPvPButton() {
		return PvP;
	}
	public Button_Navigation getTrainButton() {
		return Train;
	}
	public Button_Navigation getBackButton() {
		return back;
	}
}
