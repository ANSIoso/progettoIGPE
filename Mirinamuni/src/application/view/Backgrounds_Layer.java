package application.view;

import java.awt.BorderLayout;
import java.awt.Graphics;

import javax.swing.JPanel;

// classe che si comporta come il "Layer" sul quale vengono "disegnati" gli sfondi

public class Backgrounds_Layer extends JPanel{
	private static final long serialVersionUID = -5678832615799647277L;
	private Backgrounds_All backgrounds = new Backgrounds_All();
	
	private int sizeX;
	private int sizeY;
	
	public Backgrounds_Layer(int sizeX, int sizeY) {
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		
		this.setLayout(new BorderLayout());
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(backgrounds.getCurrentBackground().getCurrentFrame(), 0, 0, sizeX, sizeY, null);
	}
	
	public void update() {
		backgrounds.update();
		repaint();
	}
	
	// metodo che cambia lo sfondo attualmente presente
	public void changeBackground(int stage) {
		backgrounds.changeBackground(stage);
	}
	
}
