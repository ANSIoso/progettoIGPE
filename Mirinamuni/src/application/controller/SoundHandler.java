package application.controller;

import java.util.HashMap;

import application.view.Sound;

// classe che si occupa di gestire tutta la parte sonora dell'applicazione
public class SoundHandler {

	private static SoundHandler instance = null;
  
	private HashMap <String,Sound> effects;
	private HashMap <String,Sound> themes;
	String currentClip;
  
	// caricamento di tutti i suoni
	private  SoundHandler () {
		effects = new HashMap<String,Sound>();
		themes = new HashMap<String,Sound>();
    
		addTheme("Theme_Menu");
		addTheme("Theme_Battle");
	    addEffect("Button_Click");
	    addEffect("Effect_Sword_noHit_1");
	    addEffect("Effect_Sword_noHit_2");
	    addEffect("Effect_Sword_Hit_1");
	    addEffect("Effect_Sword_Hit_2");
	    addEffect("Effect_Start_Fight");
	    addEffect("Effect_Endbattle");
	}
  
	// impostazione della situazione "iniziale"
	public void init() {
		startTheme("Theme_Menu");
		setToNormalVolume("Theme_Menu", -10);
		startTheme("Theme_Battle");
		setToMinVolume("Theme_Battle");
	}
	
	public static SoundHandler getInstance() {
		if (instance == null)
			instance = new SoundHandler();
		return instance;
	}
  
	public void addTheme(String theme) {
		themes.put(theme, new Sound(theme, "Themes"));
	}
  
	public void addEffect(String effect) {
		effects.put(effect, new Sound(effect, "Effects"));
	}
  
	public void startTheme(String track_name) {
		if(themes.containsKey(track_name)) 
			themes.get(track_name).StartTheme();  
	}
  
	public void stopTheme(String track_name) {
		if(themes.containsKey(track_name)) 
			themes.get(track_name).StopTheme();
	}
  
	public void startEffect(String track_name) {
		if(effects.containsKey(track_name))
			effects.get(track_name).StartEffect();
	}
	// metodi per ridurre il volume
	public void reduceThemesVolume () {
	  for(String theme : themes.keySet()) {
		  themes.get(theme).reduceVolume();
	  }
	}
  
	public void increaseThemesVolume () {
		for(String theme : themes.keySet()) {
			themes.get(theme).increaseVolume();
		}
	}
  
	public void reduceEffectVolume () {
		for(String effect : effects.keySet()) {
			effects.get(effect).reduceVolume();
		}
	}
  
	public void increaseEffectVolume () {
		for(String effect : effects.keySet()) {
			effects.get(effect).increaseVolume();
		}
	}
  
	public void setToMinVolume(String track_name) {
		themes.get(track_name).setToMinVolume();
	}
	
	public void setToNormalVolume(String track_name, int vol) {
		themes.get(track_name).setToNormalVolume(vol);
	}
	
	// metodi per cambiare tema generale
	public void switch_to_battle() {
		SoundHandler.getInstance().setToNormalVolume("Theme_Battle", -10);
		SoundHandler.getInstance().setToMinVolume("Theme_Menu");
	}
	
	public void switch_to_menu() {
		SoundHandler.getInstance().setToNormalVolume("Theme_Menu", -10);
		SoundHandler.getInstance().setToMinVolume("Theme_Battle");
	}
}