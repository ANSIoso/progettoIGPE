package application.controller;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import application.view.Button_Navigation;
import application.view.View_Battle_Pause;

// controller associato alla schermata di pausa della battaglia
// gestisce gli effetti dei clic sugli elementi dell'interfaccia
public class Controller_Battle_Pause {

    public Controller_Battle_Pause (View_Battle_Pause p) {
        addMouseBackFunction(p.getBackButton());
        addKeyBackFunction(p);
        
        addFunction(p.getExitButton());
    }

    private void addKeyBackFunction(View_Battle_Pause p) {
		p.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				super.keyPressed(e);
				
				SceneHandler.getInstance().back();
			}
		});
	}

	private void addMouseBackFunction(Button_Navigation button) {
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);

                SceneHandler.getInstance().back();
                SoundHandler.getInstance().startEffect("Button_Click");
            }
        });
    }

    private void addFunction(Button_Navigation button) {
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);

                SceneHandler.getInstance().windowChanger("Menu");
                SoundHandler.getInstance().startEffect("Button_Click");
            }
        });
    }
}