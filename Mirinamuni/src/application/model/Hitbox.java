package application.model;


public class Hitbox {
	// I pg si muoveranno solo sull'asse x per potersi sincronizzare nei movimenti
	// la loro posizione sull'asse y sarà sempre sincronizzata e decisa differentemente

	// begin_pointX sarà l'effettivo riverimento per la posizione del pg
	// per "disegnarlo"
	// per "muoverlo" (modificando la posizione "begin" si influirà anche sulla end)
	private int begin_pointX;
	private int end_pointX  ;
	private int begin_body = begin_pointX + 250;
	private int end_body   = end_pointX   - 250;
	
	
	private int dim         ;
	
	// l'accesso a begin è privato perchè al suo aggiornamento è legato 
	// il "ricalcolo" hitbox
	
	public Hitbox(int initialX, int dim) {
		// dim indica quanto sarà "larga" la Hitbox (l'altezza di essa rimane standard)
		this.dim = dim;
		
		begin_pointX = initialX;
	}
	
	public boolean colliding(Hitbox enemy_hitbox) {
		// se anche un solo "punto" delle due si "tocca" allora le due hitbox
		// sono in "collisione"
		
		if((enemy_hitbox.begin_body <= begin_body && begin_body <= enemy_hitbox.end_body ) ||
		   (enemy_hitbox.begin_body <= end_body   && end_body   <= enemy_hitbox.end_body )   )
			return true;
		
		return false;
	}
	
	public boolean swordColliding(Hitbox enemy_hitbox) {
		// se anche un solo "punto" delle due si "tocca" allora le due hitbox
		// sono in "collisione"
		int swordHitbox = 270;
		
		if((enemy_hitbox.begin_body - swordHitbox <= begin_body && begin_body <= enemy_hitbox.end_body + swordHitbox) ||
		   (enemy_hitbox.begin_body - swordHitbox <= end_body   && end_body   <= enemy_hitbox.end_body + swordHitbox)   )
			return true;
		
		return false;
	}
	
	public void setBegin_pointX(int posX) {
		// ricalcolo della nuova hitbox 
		// essa sarà compresa dal "punto iniziale" |--------dim--------| "punto finale"
		
		begin_pointX = posX;
		end_pointX = begin_pointX + dim;
		
		begin_body = begin_pointX + 300;
		end_body   = end_pointX   - 300;
	}
	
	public int getBegin_pointX() {
		return begin_pointX;
	}
	
	public int getDim() {
		return dim;
	}
}








