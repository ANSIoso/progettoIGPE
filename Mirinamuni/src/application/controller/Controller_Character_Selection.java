package application.controller;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import application.model.Battle;
import application.view.Button_Character_Choose;
import application.view.Button_Character_Player;
import application.view.Button_Navigation;
import application.view.View_Character_Selection;

// classe che si occupa di gestire le interazioni con la schermata di selezione dei personaggi
public class Controller_Character_Selection {
	private View_Character_Selection m;
	
	private int chooser = Battle.NOBODY;
	
	public Controller_Character_Selection(View_Character_Selection m) {
		this.m = m;
		
		addFunction(this.m.getP1Button());
		addFunction(this.m.getP2Button());
		addFunction(this.m.getP3Button());
		addFunction(this.m.getP4Button());
		
		addChooseFunction(this.m.getButton_g1());
		addChooseFunction(this.m.getButton_g2());
		
		addNavigationFunction(this.m.getStartButton());
		
		addMouseBackFunction(this.m.getBackButton());
		addKeyBackFunction(m);
	}
	
	// con navigation function si intende la funzione di spostamentotra le pagine
	// mediante la funzione dello scene handler
	private void addNavigationFunction(Button_Navigation button) {
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				super.mouseReleased(e);
				
				if(Battle.getInstance().getMode() == Battle.SINGLEPLAYER) {
					Random ia_pg = new Random();
					m.getButton_g2().choosePg(ia_pg.nextInt(4));
				}
				if(Battle.getInstance().getMode() == Battle.TRAINING)
					m.getButton_g2().choosePg(-1);
				
				if(m.getButton_g1().getPG() != Button_Character_Player.UNCHOSEN && m.getButton_g2().getPG() != Button_Character_Player.UNCHOSEN) {
					Battle.getInstance().setupBattleParameters(m.getButton_g1().getPG(), m.getButton_g2().getPG());
					SceneHandler.getInstance().windowChanger("Battle");
					SoundHandler.getInstance().startEffect("Effect_Start_Fight");
					SoundHandler.getInstance().startEffect("Button_Click");
				}
				
				chooser = Battle.NOBODY;
			}
		});
		
	}

	// aggiunta della funzione di selezione di un determinato personaggio 
	private void addChooseFunction(Button_Character_Player button) {
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				super.mouseReleased(e);
				
				if(button.getPlayer() == Battle.G1)
					chooser = Battle.G1;
				
				if(button.getPlayer() == Battle.G2)
					chooser = Battle.G2;
				
				SoundHandler.getInstance().startEffect("Button_Click");
			}
		});
		
	}

	// aggiunta della funzione attraverso la quale si indica se a scegliere un personaggio è
	// il g1 oppure il g2
	private void addFunction(Button_Character_Choose button) {
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				super.mouseReleased(e);
				
				if(chooser == Battle.G1) {
					m.getButton_g1().choosePg(button.getPG());
				}if(chooser == Battle.G2) {
					m.getButton_g2().choosePg(button.getPG());
				}if(chooser == Battle.NOBODY) {
					m.getButton_g1().choosePg(button.getPG());
					chooser = Battle.G2;
				}
				
				SoundHandler.getInstance().startEffect("Button_Click");
				m.getButton_g1().repaint();
				m.getButton_g2().repaint();
			}
		});
	}
	
	private void addMouseBackFunction(Button_Navigation button) {
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				super.mouseReleased(e);
				
				m.getButton_g1().resetChoose();
				m.getButton_g2().resetChoose();
				
				SceneHandler.getInstance().back();
				chooser = Battle.NOBODY;
				
				SoundHandler.getInstance().startEffect("Button_Click");
			}
		});
	}
	
	private void addKeyBackFunction(View_Character_Selection m) {
		m.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				super.keyPressed(e);
				
				SceneHandler.getInstance().back();
			}
		});
	}
}
