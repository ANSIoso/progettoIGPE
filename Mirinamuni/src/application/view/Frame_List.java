package application.view;

import java.awt.Image;
import java.util.ArrayList;

public class Frame_List {
	
	// classe con una lista di frame che saranno riprodotti in loop 
	
	private ArrayList<Image> frames;
	private int index;
	private Image currentFrame;
	private boolean loopped;
	
	public Frame_List(ArrayList<Image> frames, boolean loopped) {
		this.loopped = loopped;
		this.index = 0;
		this.frames = frames;
		this.currentFrame = null;
		if(frames.size() > 0)
			this.currentFrame = frames.get(0);
	}
	// metodo che permette di farte andare in loop le immagini
	public boolean update() {
		index++;
		if(index >= frames.size()) {
			// se non è in loop la booleana sarà impostata  a false
			if(loopped)
				index = 0;
			else
				index--;
			return false;
		}
		
		currentFrame = frames.get(index);
		return true;
	}
	
	public void resetAnimation() {
		index = 0;
	}
	// metodo che mi permette di prendere il frame attuale
	public Image getCurrentFrame() {
		return currentFrame;
	}
}
