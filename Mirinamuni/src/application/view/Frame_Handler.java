package application.view;

import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javax.imageio.ImageIO;

import application.controller.SceneHandler;
import application.model.General_Errors;

public class Frame_Handler {
	private static Frame_Handler instance;
	
	// classe utilizzata per il caricamento dei vari frame utilizzati per le animazioni
	
	public Frame_Handler() {
	}
	
	public static Frame_Handler getIstance() {
		if(instance == null)
			instance = new Frame_Handler();
		return instance;
	}
	
	public ArrayList<Image> getResources(String name, String path, boolean reverse) {
		ArrayList<Image> frames = new ArrayList<Image>();
		try {
			path += name + "/";
			
			File f = new File(Frame_Handler.class.getClassLoader().getResource(path).getPath());
			ArrayList<File> listOfFrames = new ArrayList<File>();

			for (File r : f.listFiles()) {
				listOfFrames.add(r);
			}

			Collections.sort(listOfFrames, new Comparator<File>() {
				@Override
				public int compare(File o1, File o2) {
					return o1.getName().compareTo(o2.getName());
				}
			});

			for (File img : listOfFrames) {
				Image i = ImageIO.read(Backgrounds_All.class.getClassLoader().getResourceAsStream(path + img.getName()));
				
				// inverto le immagini se necessario
				if(reverse) {
					AffineTransform  tx = AffineTransform.getScaleInstance(-1, 1);
					tx.translate(-i.getWidth(null), 0);
					AffineTransformOp  op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
					i = op.filter((BufferedImage) i, null);
				}
					
				
				frames.add(i);
			}
		} catch (IOException e) {
			SceneHandler.getInstance().showErrors(General_Errors.LOADIMGERROR);
		}
		
		return frames;
	}
}
