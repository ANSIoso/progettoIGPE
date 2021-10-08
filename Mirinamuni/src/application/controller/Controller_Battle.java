package application.controller;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import application.Loop;
import application.model.Battle;
import application.model.PlayerActions;
import application.view.View_Battle;

// controller della schermata di battaglia essa gestisce 
// i controlli della tastiera e gli effetti che essi hanno sulla battaglia.
// In oltre essa gestisce anche l'avvio del bot nel caso si scelga "VSIA"

public class Controller_Battle implements KeyListener {
	private View_Battle battle;
	private Loop<View_Battle> bloop;
	
	
	private int result;
	ResultListener listener;
	
	IA ai;
	
	public Controller_Battle(View_Battle battle) {
		this.setBattle(battle);

		// si gestisce l'avvio del bot e l'inserimento/rimozione di elementi a schermo
		// a seconda che la schermata ottenga o perda il "focus" (ossia quella con la quale si sta interagendo)
		battle.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				super.focusGained(e);

				bloop = new Loop<View_Battle>(battle);
				bloop.start();
				
				if(Battle.getInstance().getMode() == Battle.SINGLEPLAYER) {
					ai = new IA(battle);
					ai.start();
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				super.focusLost(e);

				bloop.interrupt();
				if(Battle.getInstance().getMode() == Battle.SINGLEPLAYER) 
					ai.interrupt();
				
				battle.reset();
			}
		});
		
		listener = new ResultListener(battle);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// exit function
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			if(Battle.getInstance().getWinner() == Battle.NOBODY)
				SceneHandler.getInstance().windowChanger("Pause");
			else {
				SceneHandler.getInstance().windowChanger("Menu");
			}
		}
		
		// movement Function
		if(e.getKeyCode() == KeyEvent.VK_A)
			Battle.getInstance().move(Battle.G1, PlayerActions.RETREAT);

		if(e.getKeyCode() == KeyEvent.VK_D)
			Battle.getInstance().move(Battle.G1, PlayerActions.ADVANCE);
		
		
		if(Battle.getInstance().getMode() == Battle.MULTIPLAYER) {	
			if(e.getKeyCode() == KeyEvent.VK_LEFT)
				Battle.getInstance().move(Battle.G2, PlayerActions.RETREAT);
				
			if(e.getKeyCode() == KeyEvent.VK_RIGHT)
				Battle.getInstance().move(Battle.G2, PlayerActions.ADVANCE);
		}
		
		// battle function
		if(e.getKeyCode() == KeyEvent.VK_W) {
			result = Battle.getInstance().hit(Battle.G1, PlayerActions.HEAVY);	
			listener.resultPerformer(PlayerActions.HEAVY, result);
		}
		if(e.getKeyCode() == KeyEvent.VK_S) {
			result = Battle.getInstance().hit(Battle.G1, PlayerActions.NORMAL);
			listener.resultPerformer(PlayerActions.NORMAL, result);
		}
		
		
		if(Battle.getInstance().getMode() == Battle.MULTIPLAYER) {	
			if(e.getKeyCode() == KeyEvent.VK_UP) {
				result = Battle.getInstance().hit(Battle.G2, PlayerActions.HEAVY);
				listener.resultPerformer(PlayerActions.HEAVY, result);
			}
			if(e.getKeyCode() == KeyEvent.VK_DOWN) {
				result = Battle.getInstance().hit(Battle.G2, PlayerActions.NORMAL);
				listener.resultPerformer(PlayerActions.NORMAL, result);
			}
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// movement Function
		if (e.getKeyCode() == KeyEvent.VK_A) {
			Battle.getInstance().stop(Battle.G1);
		}

		if (e.getKeyCode() == KeyEvent.VK_D) {
			Battle.getInstance().stop(Battle.G1);
		}

		if (Battle.getInstance().getMode() == Battle.MULTIPLAYER) {
			if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				Battle.getInstance().stop(Battle.G2);
			}

			if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				Battle.getInstance().stop(Battle.G2);
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	public View_Battle getBattle() {
		return battle;
	}

	public void setBattle(View_Battle battle) {
		this.battle = battle;
	}
}
