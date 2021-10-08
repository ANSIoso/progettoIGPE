package application.view;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.Insets;
import java.io.File;
import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.JTextArea;

import application.File_Reader;
import application.controller.SceneHandler;
import application.model.General_Errors;

public class View_Character_Info extends View_Character{
	
	//Classe per i settaggi base per la Character view
	
	private static final long serialVersionUID = 5014570559073914582L;
	
	Panel_Character_Information story;
	Panel_Character_Information stat_info;
	
	public View_Character_Info() {
		//inserimento dei pannelli laterali per info personaggio 
		gbc.insets = new Insets(10, 0, 0, 20);
		story     = new Panel_Character_Information();
		gbc.gridx = 0;
		gbc.gridy = 0;
		east.add(story, gbc);
		stat_info = new Panel_Character_Information();
		gbc.gridx = 1;
		gbc.gridy = 0;
		east.add(stat_info, gbc);
	}
	
	public void load_info(int character) {
		story.reset();
		stat_info.reset();
		
		// settaggio font
		Font myfont = null;
		
		try {
		    Font customFont = Font.createFont(Font.TRUETYPE_FONT, new File(Panel_Character_Stat.class.getClassLoader().getResource("Fonts/slkscrb.ttf").getPath() )).deriveFont(12f);
		    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		    
		    ge.registerFont(customFont);
		    myfont = customFont;
		} catch (IOException | FontFormatException e) {
			SceneHandler.getInstance().showErrors(General_Errors.LOADFONTERROR);
		}
		// settaggio font
		
		
		// display la storia del personaggio
	    String story_text = File_Reader.getInstance().read("Characters/Stories/Story_" + character + ".txt");
	    JLabel space1 = new JLabel(" ");
	    JLabel space2 = new JLabel("STORY");
 		JTextArea st = new JTextArea(story_text);
 		st.setLineWrap(true);
 		st.setEditable(false);
 		st.setOpaque(false);
 		
 		space2.setAlignmentX(CENTER_ALIGNMENT);
 		st.setAlignmentX(CENTER_ALIGNMENT);
 		
 		space2.setFont(myfont);
 		st.setFont(myfont);
 		
 		story.add(space1);
 		story.add(space2);
	    story.add(st);
	    
	    // display le info sul personaggio
 		for(int i = 0; i < 4; i++) {
 			Panel_Character_Stat stat = new Panel_Character_Stat(character, i, myfont);
 			stat_info.add(stat);
 		}
 		
 		JLabel space3 = new JLabel(" ");
 		JLabel space4 = new JLabel(" ");
 		stat_info.add(space3);
 		stat_info.add(space4);
	}
	
	public void resetInfo() {
		story.removeAll();
		stat_info.removeAll();
	}
}
