package application.controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import application.view.Button_Navigation;
import application.view.View_Battle_Pause;

// controller associato alla schermata di pausa della battaglia
// gestisce gli effetti dei clic sugli elementi dell'interfaccia
public class Controller_Battle_Pause {

    public Controller_Battle_Pause (View_Battle_Pause p) {
        addBackFunction(p.getBackButton());
        addFunction(p.getExitButton());
    }

    private void addBackFunction(Button_Navigation button) {
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