package application.view;

import java.awt.Image;
import java.util.HashMap;

import application.model.PlayerActions;

public class View_Character_Animation_All {
	
	// classe che carica le sprites sul programma
	
	private HashMap<Integer, Frame_List> animations;
	private Frame_List currentAnimation;
	
	private int PL;
	
	private String path;
	
	public View_Character_Animation_All(int PL, boolean reverse) {
		if(PL < 0 || PL > 3)
			PL = 0;
		
		animations = new HashMap<Integer, Frame_List>();
		
		path = "Characters/Character_" + PL + "/";
		
		animations.put(PlayerActions.ADVANCE, new Frame_List(Frame_Handler.getIstance().getResources("Run"     , path, false  ), true ));
		animations.put(PlayerActions.RETREAT, new Frame_List(Frame_Handler.getIstance().getResources("Run"     , path, true   ), true ));
		animations.put(PlayerActions.NORMAL , new Frame_List(Frame_Handler.getIstance().getResources("Attack_1", path, reverse), true ));
		animations.put(PlayerActions.HEAVY  , new Frame_List(Frame_Handler.getIstance().getResources("Attack_2", path, reverse), true ));
		animations.put(PlayerActions.IDLE   , new Frame_List(Frame_Handler.getIstance().getResources("Idle"    , path, reverse), true ));
		animations.put(PlayerActions.TAKEHIT, new Frame_List(Frame_Handler.getIstance().getResources("Hit"     , path, reverse), true ));
		animations.put(PlayerActions.DEATH  , new Frame_List(Frame_Handler.getIstance().getResources("Death"   , path, reverse), false));
		
		currentAnimation = animations.get(PlayerActions.IDLE);
	}
	
	Image getCurrentImage() {		
		return currentAnimation.getCurrentFrame();
	}
	
	// metodo del cambio di animazione 
	public void changeAnimation(int type) {
		if(currentAnimation == animations.get(PlayerActions.DEATH) || !animations.containsKey(type))
			return;
		
		if(currentAnimation == animations.get(PlayerActions.NORMAL) || currentAnimation == animations.get(PlayerActions.HEAVY))
			return;
		
		currentAnimation = animations.get(type);
	}
	
	void update() {
		if (currentAnimation == null)
			return;
		if (!currentAnimation.update())
			currentAnimation = animations.get(PlayerActions.IDLE);
	}

	public int getPL() {
		return PL;
	}

	public void setPL(int pL) {
		PL = pL;
	}
}
