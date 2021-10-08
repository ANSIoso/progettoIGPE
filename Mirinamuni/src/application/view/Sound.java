package application.view;

import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import application.controller.SceneHandler;
import application.model.General_Errors;

public class Sound {
	
	// classe base degli audio con le funzioni che verrano richiamate nel SoundHandler
	
	private AudioInputStream audio;
	private Clip clip;
  
	public Sound (String track, String type){
		try {
			audio = AudioSystem.getAudioInputStream(getClass().getResourceAsStream("/" + type + "/" + track + ".wav"));
			clip = AudioSystem.getClip();  
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
			SceneHandler.getInstance().showErrors(General_Errors.LOADAUDIOERROR);
		}
	}	
  
	// Funzione che fa partire i temi
	public void StartTheme () {
		try {
			if(clip != null)
				if(!clip.isOpen()) {
					clip.open(audio);
					clip.loop(Clip.LOOP_CONTINUOUSLY);
					clip.setFramePosition(0);
					clip.start();
      	 }
		} catch (IOException | LineUnavailableException e) {
			SceneHandler.getInstance().showErrors(General_Errors.LOADAUDIOERROR);
		}
	}
	// Funzione che ferma il tema in esecuzione
	public void StopTheme() {
		if(clip != null) {
			clip.stop();
			clip.close();
		}
	}
	// Funzione che fa partire gli effetti
	public void StartEffect() {
		try {
			if(clip != null)
				if(!clip.isOpen()) {
					clip.open(audio);
					clip.setFramePosition(0);
					clip.start();
				}else {
					clip.stop();
					clip.setFramePosition(0);
					clip.start();
				}
		} catch (IOException | LineUnavailableException e) {
			SceneHandler.getInstance().showErrors(General_Errors.LOADAUDIOERROR);
		}
	}
	// Funzioni che regola i volumi
	public void reduceVolume() {
		if (clip != null) {
			FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			float value = gainControl.getValue();
			value -= 2.0f;
			if (value >= gainControl.getMinimum())
				gainControl.setValue(value);
		}
	}

	public void increaseVolume() {
		if (clip != null) {
			FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			float value = gainControl.getValue();
			value += 2.0f;
			if (value <= gainControl.getMaximum())
				gainControl.setValue(value);
		}
	}
  
	public void setToMinVolume() {
		if (clip != null) {
			FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			gainControl.setValue(-80);
		}
	}
  
	public void setToNormalVolume(int vol) {
		if (clip != null) {
			FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			gainControl.setValue(vol);
		}
	}
}