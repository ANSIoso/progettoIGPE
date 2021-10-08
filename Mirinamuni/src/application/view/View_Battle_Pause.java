package application.view;

import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import application.controller.SceneHandler;
import application.model.General_Errors;

public class View_Battle_Pause extends JPanel {
	
	// Classe che definisce la schermata di pausa della battaglia
	
    private static final long serialVersionUID = -7403532620644123735L;

    private GridBagConstraints gbc = new GridBagConstraints();
    private Button_Navigation Back;
    private Button_Navigation Exit;

    public View_Battle_Pause(){

        JPanel gbcp = new JPanel();

        this.setOpaque(false);
        gbcp.setOpaque(false);

        this.setLayout(new GridBagLayout());

        gbc.insets = new Insets(5, 5, 5, 5);

        Back = new Button_Navigation("Back", Button_Navigation.RECTANGULAR);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbcp.add(Back, gbc);

        Exit = new Button_Navigation("Exit", Button_Navigation.RECTANGULAR);
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbcp.add(Exit, gbc);

        this.add(gbcp);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        File path = new File(View_Info_Game.class.getClassLoader().getResource("Battle/Battle_Pause.png").getPath());
        Image b;
        try {
            b = ImageIO.read(path);
            g.drawImage(b, 0, 0, null);
        } catch (IOException e) {
        	SceneHandler.getInstance().showErrors(General_Errors.LOADUIERROR);
        }
    }

    public Button_Navigation getBackButton() {
        return Back;
    }

    public Button_Navigation getExitButton() {
        return Exit;
    }
}