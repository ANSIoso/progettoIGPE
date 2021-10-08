package application.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;

import javax.swing.JPanel;

import application.controller.SceneHandler;
import application.model.Battle;
import application.model.Character;
import application.model.General_Errors;

public class View_Battle extends JPanel{
	
	// Classe che definisce la schermata di battaglia
	
	private static final long serialVersionUID = 3877796488136461257L;
	
	// Parametri vari
	
	private static final int RINGSTART   = -145;
	private static final int RINGHEIGHT  = 250;
	private static final int CHARHEIGHT  = 390;
	
	private static final int BFULL       = 400;
	private static final int BPOSITIONX  = 10;
	private static final int BPOSITIONY  = 100;
	private static final int BHEIGHT     = 30;
	
	private static final int CHARINFOY   = 650;
	private static final int CHARINFOSIZE= 10;

	private View_Character_Animation_All graphigraphic_g1;
	private View_Character_Animation_All graphigraphic_g2;
	
	private Character g1;
	private int g1_tot_life;
	private int g1_life;
	private int g1_stamina;
	
	private Character g2;
	private int g2_tot_life;
	private int g2_life;
	private int g2_stamina;
	
	Font myfont;
	
	View_Battle_Win win_screen;
	
	public View_Battle() {
		
		// settaggio font
		myfont = null;
		
		try {
		    Font customFont = Font.createFont(Font.TRUETYPE_FONT, new File(Panel_Character_Stat.class.getClassLoader().getResource("Fonts/slkscrb.ttf").getPath() )).deriveFont(72f);
		    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		    
		    ge.registerFont(customFont);
		    myfont = customFont;
		} catch (IOException | FontFormatException e) {
			SceneHandler.getInstance().showErrors(General_Errors.LOADFONTERROR);
		}
		// settaggio font
		
		this.setFocusable(true);
		this.setOpaque(false);
		this.setLayout(new BorderLayout());
	}
	
	@Override
	public void validate() {
		super.validate();
		
		g1 = Battle.getInstance().getG1();
		g2 = Battle.getInstance().getG2();
		
		g1_tot_life = Character.getCharactersStats(g1.getName())[Character.LIFE];
		g1_life = g1.getCharacterStats(Character.LIFE);

		g2_tot_life = Character.getCharactersStats(g2.getName())[Character.LIFE];
		g2_life = g2.getCharacterStats(Character.LIFE);
		
		graphigraphic_g1 = new View_Character_Animation_All(g1.getName(), false);
		graphigraphic_g2 = new View_Character_Animation_All(g2.getName(), true );
	}
	
	// metodo di visualizzazione delle sprites su schermo
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		// visualizzazione delle barre su schermo
		g.setColor(Color.red);
		g.fillRect(                                                             BPOSITIONX, BPOSITIONY,                         BFULL, BHEIGHT);
		g.fillRect(this.getSize().width - ((BFULL * g2_tot_life)/g2_tot_life) - BPOSITIONX, BPOSITIONY,                         BFULL, BHEIGHT);
		
		g.setColor(Color.green);
		g.fillRect(                                                         BPOSITIONX,      BPOSITIONY, (BFULL * g1_life)/g1_tot_life, BHEIGHT);
		g.fillRect(this.getSize().width - ((BFULL * g2_life)/g2_tot_life) - BPOSITIONX,      BPOSITIONY, (BFULL * g2_life)/g2_tot_life, BHEIGHT);
		
		g.setColor(Color.YELLOW);
		g.fillRect(                                                        BPOSITIONX, BPOSITIONY + 30,       ((BFULL - 100) * g1_stamina)/15, BHEIGHT - 20);
		g.fillRect(this.getSize().width - ((BFULL-100) * g2_stamina)/15 - BPOSITIONX, BPOSITIONY + 30,       ((BFULL - 100) * g2_stamina)/15, BHEIGHT - 20);
		// visualizzazione delle barre su schermo
		// visualizzazione timer
		g.setColor(Color.red);
		g.setFont(myfont);
		g.drawString(Battle.getInstance().getTime() + "", 595, 150);
		// visualizzazione timer
		
		g.setColor(Color.red);
		graphigraphic_g1.changeAnimation(g1.getActual_Action());
		g.drawImage(graphigraphic_g1.getCurrentImage(), RINGSTART + g1.getPosition(), RINGHEIGHT, g1.getDim(), CHARHEIGHT, null);
		g.fillRect (RINGSTART + g1.getPosition() + 270, CHARINFOY, 100, CHARINFOSIZE);
		
		g.setColor(Color.blue);
		graphigraphic_g2.changeAnimation(g2.getActual_Action());
		g.drawImage(graphigraphic_g2.getCurrentImage(), RINGSTART + g2.getPosition(), RINGHEIGHT, g2.getDim(), CHARHEIGHT, null);
		g.fillRect (RINGSTART + g2.getPosition() + 270, CHARINFOY, 100, CHARINFOSIZE);
	}
	
	public boolean update() {
		g1_life = g1.getCharacterStats(Character.LIFE);
		g2_life = g2.getCharacterStats(Character.LIFE);
		
		g1_stamina = g1.getRecovery();
		g2_stamina = g2.getRecovery();
		
		Battle.getInstance().timeStep();
		graphigraphic_g1.update();
		graphigraphic_g2.update();
		
		repaint();
		return addWinScreen();
	}
	
	// metodo di aggiunta della schermata di vittoria appena la partita finisce
	private boolean addWinScreen() {
		int player = Battle.getInstance().getWinner();
		if(player != Battle.NOBODY) {
			int character = 0;
			
			if(player == Battle.G1)
				character = Battle.getInstance().getG1().getName();
			if(player == Battle.G2)
				character = Battle.getInstance().getG2().getName();
			
			win_screen = new View_Battle_Win(player, character);
			this.add(win_screen);
			revalidate();
			return true;
		}
		return false;
	}
	// appena si torna al menu resetta la schermata di vittoria, così non ci sarà di nuovo alla prossima partita
	public void reset() {
		if(Battle.getInstance().getWinner() != Battle.NOBODY)
			this.remove(win_screen);
	}
	
	public View_Character_Animation_All getgg1() {
		return graphigraphic_g1;
	}
	
	public View_Character_Animation_All getgg2() {
		return graphigraphic_g2;
	}
}
