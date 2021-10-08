package application.view;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import application.controller.SceneHandler;
import application.model.General_Errors;

public class Button_Navigation extends JLabel {
	
	// classe che definisce tutti i bottoni che servono a spostarti attraverso le schermate
	
	private static final long serialVersionUID = -4800289877676404872L;
	
	public static final int UNCLICKED = 0;
	public static final int CLICKED = 1;
	public static final int ENTERED = 2;
	
	public static final int RECTANGULAR = 3;
	public static final int SQUARE = 4;
	public static final int BACK = 5;
	
	private int type;
	private Image[] images = new Image[2];

	Button_Navigation(String name, int type) {
		super();
		this.type = type;
		
		try {
			images[0] = ImageIO.read(Button_Navigation.class.getClassLoader().getResourceAsStream("Buttons/Button_" + name + "_1.png"));
			images[1] = ImageIO.read(Button_Navigation.class.getClassLoader().getResourceAsStream("Buttons/Button_" + name + "_2.png"));
		} catch (IOException e) {
			SceneHandler.getInstance().showErrors(General_Errors.LOADUIERROR);
		}
		switch(type) {
		case RECTANGULAR :
			this.setPreferredSize(new Dimension(Button_DimensionSettings.RECTANGULAR_BUTTON_X_SIZE, Button_DimensionSettings.RECTANGULAR_BUTTON_Y_SIZE+3));
			break;
			
		case SQUARE : 
			this.setPreferredSize(new Dimension(Button_DimensionSettings.SQUARE_BUTTON_X_SIZE, Button_DimensionSettings.SQUARE_BUTTON_Y_SIZE+3));
			break;

		case BACK :
			this.setPreferredSize(new Dimension(Button_DimensionSettings.BACK_BUTTON_X_SIZE, Button_DimensionSettings.BACK_BUTTON_Y_SIZE + 3));
			break;		
		}
		
		addFunction();
		setImage(UNCLICKED);
	}
	
	// settiamo i comportamenti grafici generali del bottone in base all'azione del mouse 
	public void addFunction() {
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				super.mouseEntered(e);
				setImage(ENTERED);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				super.mouseExited(e);
				setImage(UNCLICKED);
			}
			@Override
			public void mousePressed(MouseEvent e) {
				super.mousePressed(e);
				setImage(CLICKED);
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				super.mouseReleased(e);
				setImage(UNCLICKED);
			}
		});
	}
	
	
	// settiamo le dimensioni del bottone in base al tipo 
	public void setImage(int state) {
		
		ImageIcon img;
		int x = 0, y = 0;
		
		switch(type) {
		case RECTANGULAR :
			x = Button_DimensionSettings.RECTANGULAR_BUTTON_X_SIZE;
			y = Button_DimensionSettings.RECTANGULAR_BUTTON_Y_SIZE;
			break;
			
		case SQUARE : 
			x = Button_DimensionSettings.SQUARE_BUTTON_X_SIZE;
			y = Button_DimensionSettings.SQUARE_BUTTON_Y_SIZE;
			break;

		case BACK :
			x = Button_DimensionSettings.BACK_BUTTON_X_SIZE;
			y = Button_DimensionSettings.BACK_BUTTON_Y_SIZE;
			break;		
		}
		
		if(state == ENTERED){
			img = new ImageIcon(images[0].getScaledInstance(x, y+5, Image.SCALE_SMOOTH));
			this.setIcon(img);
			return;
		}
		
		img = new ImageIcon(images[state].getScaledInstance(x, y, Image.SCALE_SMOOTH));
		this.setIcon(img);
	}
	
}
