package application.model;

public class Character {
	// lista dei personaggi in base ai quali si decideranno le statistiche

	public static final int LIFE = 0;
	public static final int DAMAGE = 1;
	public static final int DEFENCE = 2;
	public static final int SPEED = 3;

	public static final int XX = 0;
	public static final int XY = 1;
	public static final int YX = 2;
	public static final int YY = 3;

	// per facilitare laccesso al pg dal game i parametri
	// avranno visibilità di package

	int name;

	// statistiche che influiscono sui "comportamenti" dei pg
	int[] stats = new int[4];

	// variabili per il disegno su scermo e per calcolo collisioni
	int dim = 650;
	Hitbox body;

	// caratteristiche che influiscono sulla capacità di movimento
	// un pg stunnato non può muoversi
	boolean stunned;
	// un pg in recupero non può attaccare
	int recovery;
	int wait;

	int acceleration = 0;

	int actual_action = PlayerActions.IDLE;
	private boolean dead = false;

	public Character(int pos, int name) {

		this.name = name;
		// impostazione delle caratteristiche a seconda del pg che si è andato a
		// scegliere

		this.stats = getCharactersStats(this.name);

		// crea zione della hitbox
		this.body = new Hitbox(pos, this.dim);
	}

	public boolean damaged(int damange, int intensity) {
		actual_action = PlayerActions.TAKEHIT;

		// calcolo del danno ricevuto anche in base all'intensità dell'attacco

		if (intensity == PlayerActions.NORMAL)
			stats[LIFE] -= (damange) / stats[DEFENCE];
		else if (intensity == PlayerActions.HEAVY)
			stats[LIFE] -= (damange * 1.2) / stats[DEFENCE];

		if (stats[LIFE] > 0)
			return false;

		// se la vite scende a zero il pg è stato sconfitto
		return true;
	}

	public int getCharacterStats(int stat) {
		return stats[stat];
	}

	// funzione che fa evolvere lo stato del pg
	public boolean checkStatus() {
		if (!dead && !stunned) {

			body.setBegin_pointX(body.getBegin_pointX() + acceleration);

			if (actual_action != PlayerActions.NORMAL && actual_action != PlayerActions.HEAVY) {
				if (acceleration > 0)
					actual_action = PlayerActions.ADVANCE;
				if (acceleration < 0)
					actual_action = PlayerActions.RETREAT;
			}

			if (acceleration == 0 && (recovery == wait || recovery == 0))
				actual_action = PlayerActions.IDLE;
			if (acceleration > 0  && (recovery == wait || recovery == 0))
				actual_action = PlayerActions.ADVANCE;
			if (acceleration < 0  && (recovery == wait || recovery == 0))
				actual_action = PlayerActions.RETREAT;
			
			
		}

		if (!dead && recovery > 0) {
			recovery--;

			if (recovery == 0)
				stunned = false;
		}
		
		if(!dead)
			return true;
		return false;
	}

	public void setAcceleration(int acceleration) {
		if (!dead) {

			this.acceleration = acceleration;
		}
	}
	
	public void death() {
		dead = true;
		recovery = 10;
		stunned = true;
		actual_action = PlayerActions.DEATH;
	}

	public int getPosition() {
		return body.getBegin_pointX();
	}

	public int getName() {
		return name;
	}

	public int getDim() {
		return dim;
	}
	
	public int getRecovery() {
		return recovery;
	}

	// stats varie dei personaggi
	public static int[] getCharactersStats(int name) {
		int[] stats = new int[4];

		switch (name) {
		case XX:
			stats[LIFE] = 10;
			stats[DAMAGE] = 30;
			stats[DEFENCE] = 20;
			stats[SPEED] = 40;
			break;

		case XY:
			stats[LIFE] = 30;
			stats[DAMAGE] = 10;
			stats[DEFENCE] = 40;
			stats[SPEED] = 20;
			break;

		case YX:
			stats[LIFE] = 20;
			stats[DAMAGE] = 40;
			stats[DEFENCE] = 10;
			stats[SPEED] = 30;
			break;

		case YY:
			stats[LIFE] = 40;
			stats[DAMAGE] = 20;
			stats[DEFENCE] = 30;
			stats[SPEED] = 10;
			break;

		default:
			stats[LIFE] = 99;
			stats[DAMAGE] = 99;
			stats[DEFENCE] = 99;
			stats[SPEED] = 99;
		}

		return stats;
	}

	public int getActual_Action() {
		return actual_action;
	}
	
	public final Hitbox getBody() {
		return body;
	}
	
	public boolean getDead() {
		return dead;
	}
}
