package application.controller;

import application.model.PlayerActions;
import application.view.View_Battle;

// classe che fa partire effetti sonori a seconda del colpo sferrato 
// e del suo risultato
public class ResultListener {
	private View_Battle battle;
	
	public ResultListener(View_Battle battle) {
		this.setBattle(battle);
		
	}
	
	public void resultPerformer(int action, int result) {
		switch (action) {
		
		case PlayerActions.NORMAL :
			if(PlayerActions.STUNNED == result)
				SoundHandler.getInstance().startEffect("Effect_Sword_Hit_1");
			if(PlayerActions.MISSED == result)
				SoundHandler.getInstance().startEffect("Effect_Sword_noHit_1");
			break;
		case PlayerActions.HEAVY :
			if(PlayerActions.STUNNED == result)
				SoundHandler.getInstance().startEffect("Effect_Sword_Hit_2");
			if(PlayerActions.MISSED == result)
				SoundHandler.getInstance().startEffect("Effect_Sword_noHit_2");	
			break;
		default:
			
		}
		
		if(result == PlayerActions.KILLED) {
			SoundHandler.getInstance().startEffect("Effect_Endbattle");
		}
	}

	public View_Battle getBattle() {
		return battle;
	}

	public void setBattle(View_Battle battle) {
		this.battle = battle;
	}
}
