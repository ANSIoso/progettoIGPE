package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import application.controller.SceneHandler;
import application.model.General_Errors;

// classe utilizzata per caricare dei testi da file di testo 
public class File_Reader {
	
	// essa viene utilizzata proprio come tool non mantiene niente in memoria
	// per questo motivo pu essere usata come "istanza"
	private static File_Reader instance = null;
	private BufferedReader b;

	private File_Reader() {
	}
	
	public static File_Reader getInstance() {
		if(instance == null)
			instance = new File_Reader();
		
		return instance;
	}
	
	public String read(String file) {
		// utilizziamo lo string builder per ottimizzare 
		// infatti con lo string normale occupa "nuova memoria" ogni volta che sfora 
		StringBuilder string = new StringBuilder(); 
		
		try {
			b = new BufferedReader(new FileReader(File_Reader.class.getClassLoader().getResource(file).getPath()));
			while(b.ready()) {
				string.append(b.readLine() + "\n");
			}
			
		} catch (IOException e) {
			SceneHandler.getInstance().showErrors(General_Errors.ERRORFOPEN);
			try {
				b.close();
				b = null;
			} catch (IOException e1) {
				SceneHandler.getInstance().showErrors(General_Errors.ERRORFCLOSE);
			}
		}
		
		return string.toString();
	}
    
}
