package application.controller;

import java.awt.BorderLayout;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import application.Loop;
import application.view.Backgrounds_All;
import application.view.View_Info_Game;
import application.view.Backgrounds_Layer;
import application.view.View_Battle;
import application.view.View_Battle_Pause;
import application.view.View_Character_Info;
import application.view.View_Character_Selection;
import application.view.View_Menu_General;
import application.view.View_Menu_Play;
import application.view.View_Title;

// classe che si occupa della navigazione tra le varie schermate
// essa contiene anche "il loop" che si occupa di aggiornare il background
public class SceneHandler {

	private static SceneHandler instance = null;

	private JFrame window;

	private HashMap<String, JPanel> screens;
	private LinkedList<String> history;

	private Backgrounds_Layer background;

	private Loop<Backgrounds_Layer> l;

	// variabile che indica se si sta tornando "indietro nelle pagine"
	private boolean back;

	// inizializzazione - caricamento - associazione dei controller alle schermate
	private SceneHandler() {
		SoundHandler.getInstance().init();

		history = new LinkedList<String>();
		screens = new HashMap<String, JPanel>();

		View_Title title = new View_Title();
		View_Menu_General menu = new View_Menu_General();
		View_Menu_Play play = new View_Menu_Play();
		View_Info_Game info = new View_Info_Game();
		View_Character_Info characters = new View_Character_Info();
		View_Character_Selection select_char = new View_Character_Selection();
		View_Battle battle = new View_Battle();
		View_Battle_Pause pause = new View_Battle_Pause();

		new Controller_Title(title);
		new Controller_Menu_General(menu);
		new Controller_Menu_Play(play);
		new Controller_Info_Game(info);
		new Controller_Characters_Info(characters);
		new Controller_Character_Selection(select_char);
		Controller_Battle battle_controller = new Controller_Battle(battle);
		battle.addKeyListener(battle_controller);
		new Controller_Battle_Pause(pause);

		screens.put("Title", title);
		screens.put("Menu", menu);
		screens.put("Play", play);
		screens.put("GameInfo", info);
		screens.put("Characters", characters);
		screens.put("Select_char", select_char);
		screens.put("Battle", battle);
		screens.put("Pause", pause);
	}

	public static SceneHandler getInstance() {
		if (instance == null)
			instance = new SceneHandler();
		return instance;
	}

	// inizializzazione del Jframe ai parametri voluti e caricamento della prima schermata
	public void init(JFrame window) {
		this.window = window;
		this.window.setSize(1280, 720);
		this.window.setUndecorated(true);
		this.window.setLocation(50, 50);

		background = new Backgrounds_Layer(window.getSize().width, window.getSize().height);
		l = new Loop<Backgrounds_Layer>(background);
		l.start();

		windowChanger("Title");

		this.window.setVisible(true);
		this.window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	// ogni schermata viene nel processo di "spostamento verso essa" 
	// - validata
	// - "focusata"
	// - aggiunta alla "lista delle pagine visitate" (Histori)
	public void windowChanger(String destination_window) {
		if (screens.containsKey(destination_window)) {
			background.removeAll();

			if (destination_window == "Battle" || destination_window == "Pause") {
				background.changeBackground(Backgrounds_All.FIGHT);
				SoundHandler.getInstance().switch_to_battle();
			} else {
				background.changeBackground(Backgrounds_All.MENU);
				SoundHandler.getInstance().switch_to_menu();
			}

			window.add(background);

			screens.get(destination_window).validate();
			background.add(screens.get(destination_window), BorderLayout.CENTER);
			screens.get(destination_window).requestFocus();

			// aggiungo la schermata alla history solo se sto "tornando indietro"
			if (!back)
				history.add(destination_window);

		}

		background.validate();
		back = false;
	}

	public void back() {
		if (history.size() >= 1) {
			history.pollLast();
		}
		
		int i = 0;
		Iterator<String> itr = history.iterator();
		while (i < history.size() - 1) {
			itr.next();
			i++;
		}

		back = true;
		windowChanger(itr.next());

	}

	public void showErrors(String text) {
		JOptionPane.showMessageDialog(window, text, "Something goes wrong", JOptionPane.ERROR_MESSAGE);
	}

	public void disposeApplication() {
		l.interrupt();
		window.dispose();
	}
}
