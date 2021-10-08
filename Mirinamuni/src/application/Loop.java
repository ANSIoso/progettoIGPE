package application;

import application.view.Backgrounds_Layer;
import application.view.Button_Character_Choose;
import application.view.View_Battle;
import application.view.View_Title;

// classe utilizzata per far girare in automatico porzioni di codice
// essa estende infatti Thread ciò gli permette di istanziare un thread per eseguire
// la porzione di codice nel metodo run quando si invoca il metodo start

public class Loop<T> extends Thread{
	// utilizzando un template e "unificando" il metodo update (ossia la porzione di codice che deve essere eseguita ad ogni tick del thread)
	// possiamo utilizzare istanze della stessa classe da "applicare" a classi diverse (ove necessario)
	private int refresh = 60;
	private T b ;
	
	public Loop(T b) {
		this.b = b;
		
		// l'unica differenza a seconda dell'oggetto a qui si applica il loop sarà nel tempo di refresh
		if(b instanceof Backgrounds_Layer)
			refresh = 60;
		if(b instanceof Button_Character_Choose)
			refresh = 200;
		if(b instanceof View_Title)
			refresh = 400;
		if(b instanceof View_Battle)
			refresh = 100;
	}
	
	@Override
	public void run() {
		super.run();
		// gli update degli oggetti gireranno fino a quando il thread non sarà interrotto
		while(!Loop.interrupted()) {
			
			if(b instanceof Backgrounds_Layer)
				((Backgrounds_Layer) b).update();
			if(b instanceof Button_Character_Choose)
				((Button_Character_Choose) b).update();
			if(b instanceof View_Title)
				((View_Title) b).update();
			if(b instanceof View_Battle) {
				if(((View_Battle) b).update())
					this.interrupt();
			}
			try {
				Thread.sleep(refresh);
			} catch (InterruptedException e) {
				return;
			}
		}
		
	}
	
	public void setRefresh(int r) {
		refresh = r;
	}

}
