package application.controller;

import java.util.Random;

import application.model.Battle;
import application.model.Character;
import application.model.PlayerActions;
import application.view.View_Battle;
// classe che implementa un'ai basilare del nemico
// le cui fai sono 2:
//                 - raggiungi il giocatore
//                 - colpisci con un attacco randomico
public class IA extends Thread{
	
	private Character enemy;
	private Character me;
	private ResultListener listener;
	
	public IA(View_Battle vb) {
		enemy = Battle.getInstance().getG1();
		me = Battle.getInstance().getG2();
        listener = new ResultListener(vb);
	}
	
	@Override
	public void run() {
		super.run();
		
		while(!IA.interrupted()) {
			if(me.getBody().colliding(enemy.getBody())) {
				Random r = new Random();
				
				int action = r.nextInt(2) + 2;
				int result = Battle.getInstance().hit(Battle.G2, action);
				listener.resultPerformer(action, result);
				
				// se uccide il nemico si ferma
				if(result == PlayerActions.KILLED)
					break;
			}else {
				int acc;
				if(enemy.getPosition() > me.getPosition())
					acc = me.getCharacterStats(Character.SPEED);
				else
					acc = -me.getCharacterStats(Character.SPEED);
					
				me.setAcceleration(acc);
			}
		}
	}
}
