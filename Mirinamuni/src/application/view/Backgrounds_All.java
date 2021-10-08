package application.view;

import java.util.HashMap;

// classe che gestisce tutti i Backgrounds e come alternarli
public class Backgrounds_All {
	public static final int MENU = 0;
	public static final int FIGHT = 1;
	private int currentStage;

	private HashMap<Integer, Frame_List> backgrounds;
	private Frame_List currentBackground;
	
	private String path = "background_";

	public Backgrounds_All() {
		backgrounds = new HashMap<Integer, Frame_List>();
		
		backgrounds.put( MENU, new Frame_List(Frame_Handler.getIstance().getResources("menu" , path, false), true));
		backgrounds.put(FIGHT, new Frame_List(Frame_Handler.getIstance().getResources("fight", path, false), true));
		
		currentBackground = backgrounds.get(MENU);
		currentStage = MENU;
	}

	public Frame_List getCurrentBackground() {
		return currentBackground;
	}

	void changeBackground(int stage) {
		if (!backgrounds.containsKey(stage) || currentStage == stage)
			return;
		
		currentStage = stage;
		currentBackground = backgrounds.get(stage);
	}

	void update() {
		if (currentBackground == null)
			return;

		currentBackground.update();
	}
}
