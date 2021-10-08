package application.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JPanel;

public class View_Menu_General extends JPanel{
	
	// Classe per la visualizzazione del menu 
	
	private static final long serialVersionUID = 807532082504438915L;

	private GridBagConstraints gbc = new GridBagConstraints();
	private Button_Navigation play;
	private Button_Navigation characters;
	private Button_Navigation gameInfo;
	private Button_Navigation exit;
	
	
	// settagi per il posizionamento dei tasti
	public View_Menu_General(){
		this.setLayout(new GridBagLayout());
		gbc.insets = new Insets(5, 5, 5, 5);
		
		this.setOpaque(false);
		
		play = new Button_Navigation("Play", Button_Navigation.RECTANGULAR);
		gbc.gridx = 0;
		gbc.gridy = 4;
		this.add(play, gbc);
		
		characters = new Button_Navigation("Characters", Button_Navigation.RECTANGULAR);
		gbc.gridx = 0;
		gbc.gridy = 5;
		this.add(characters, gbc);
		
		gameInfo = new Button_Navigation("GameInfo", Button_Navigation.RECTANGULAR);
		gbc.gridx = 0;
		gbc.gridy = 6;
		this.add(gameInfo, gbc);
		
		exit = new Button_Navigation("Exit", Button_Navigation.RECTANGULAR);
		gbc.gridx = 0;
		gbc.gridy = 7;
		this.add(exit, gbc);
	}
	
	public Button_Navigation getPlayButton() {
		return play;
	}
	public Button_Navigation getCharactersButton() {
		return characters;
	}
	public Button_Navigation getGameInfoButton() {
		return gameInfo;
	}
	public Button_Navigation getExitButton() {
		return exit;
	}
}
