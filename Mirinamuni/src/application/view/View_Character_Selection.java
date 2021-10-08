package application.view;

import java.awt.Insets;

import application.model.Battle;

public class View_Character_Selection extends View_Character{
	
	// Classe della visualizzazione della schermata della scelta del personaggio
	
	private static final long serialVersionUID = -3449645703033943286L;

	private Button_Character_Player button_g1 = new Button_Character_Player(1);
	private Button_Character_Player button_g2 = new Button_Character_Player(2);
	
	private Button_Navigation start;
	
	public View_Character_Selection() {
		start = new Button_Navigation("Start", Button_Navigation.RECTANGULAR);
		
	}
	
	// resetta la schermata e la imposta in base alla modalità scelta
	@Override
	public void validate() {
		super.validate();
		east.removeAll();
		
		gbc.insets = new Insets(10, 0, 0, 20);

		gbc.gridx = 0;
		gbc.gridy = 0;
		east.add(button_g1, gbc);
		
		if(Battle.getInstance().getMode() == Battle.MULTIPLAYER) {
			gbc.gridx = 1;
			gbc.gridy = 0;
			east.add(button_g2, gbc);
		}
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		east.add(start, gbc);
		
		button_g1.resetChoose();
		button_g2.resetChoose();
	}
	
	public Button_Character_Player getButton_g1() {
		return button_g1;
	}
	
	public Button_Character_Player getButton_g2() {
		return button_g2;
	}
	
	public Button_Navigation getStartButton() {
		return start;
	}
}
