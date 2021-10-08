package application.view;

import java.awt.BorderLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import application.controller.SceneHandler;
import application.model.General_Errors;

public class View_Info_Game extends JPanel{
	
	//Classe per la visualizzazione delle istruzioni di gioco  
	
	private static final long serialVersionUID = -7002424633649020067L;
	
	Button_Navigation back;
	
	public View_Info_Game() {
		JLabel info;
		JPanel page_end = new JPanel();
		
		this.setLayout(new BorderLayout());
		page_end.setLayout(new BorderLayout());
		
		this.setOpaque(false);
		page_end.setOpaque(false);
		
		File path = new File(View_Info_Game.class.getClassLoader().getResource("Info_Game.png").getPath());
		try {
			Image i = ImageIO.read(path);
			info = new JLabel(new ImageIcon(i));
			this.add(info, BorderLayout.CENTER);
		} catch (IOException e) {
			SceneHandler.getInstance().showErrors(General_Errors.LOADUIERROR);
		}
		
		back = new Button_Navigation("Freccetta", Button_Navigation.BACK);
		page_end.add(back, BorderLayout.LINE_END);
		this.add(page_end, BorderLayout.PAGE_END);
	}
	
	public Button_Navigation getBackButton() {
		return back;
	}
}
