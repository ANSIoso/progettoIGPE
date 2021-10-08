package application.model;

// classe che definisce i comportamenti in battaglia
public class Battle {
	// non istanziando un'effettivo oggetto per ogni volta che dobbiamo cominciare
	// una partita ma avendo un'istanza di esso se vogliamo riprendere una partita
	// rimasta in sospeso possiamo comodamente farlo
	private static Battle game = null;
	private int winner;
	private int gametime;
	private int time;

	// indicando con un "enum" che è a compiere le azioni "isoliamo"
	// l'accesso ai pg dall'esterno per indicare chi è che deve compiere
	// l'azione basta indicare PG1 o PG
	public static final int G1 = 1;
	public static final int G2 = 2;
	public static final int NOBODY = -1;

	// personaggi che partecipano alla lotta
	private Character pg1;
	private Character pg2;

	private int ring_size = 900;

	// modalità disponibili
	public static final byte TRAINING = 0;
	public static final byte SINGLEPLAYER = 1;
	public static final byte MULTIPLAYER = 2;
	private byte mode;

	// il costruttore non è raggiungibile dall'esterno della classe
	private Battle(byte mode) {
		this.mode = mode;
		this.winner = Battle.NOBODY;
	}

	// si può solo richiamarlo "impostado" la battaglia
	public static Battle setupBattleMode(byte mode) {
		game = new Battle(mode);
		return game;
	}

	public byte getMode() {
		return mode;
	}

	// oppure riprenderne una in "pausa" se essa è presente
	public static Battle getInstance() {
		return game;
	}

	public void setupBattleParameters(int pg1, int pg2) {
		time = 0;
		gametime = 99;

		this.pg1 = new Character(0, pg1);
		this.pg2 = new Character(ring_size - 1, pg2);
	}

	public byte hit(int player, int power) {
		// la funzione tornerà il pg che ha vinto nel caso ci sia stato un colpo
		// "mortale"

		// (a secondo di chi è stato a sferrare il colpo si "compilerà" la funzione
		// STRIKE)
		if (player == G1 && pg1.recovery == 0) {
			setStrikerRecovery(power, pg1);
			return strike(pg1, pg2, power);
		}

		if (player == G2 && pg2.recovery == 0) {
			setStrikerRecovery(power, pg2);
			return strike(pg2, pg1, power);
		}

		return PlayerActions.NOTHING;
	}

	private byte strike(Character striker, Character defender, int power) {
		// il colpo viene sferrato solo se i due personaggi si stanno effettivamente
		// toccando
		if (striker.body.swordColliding(defender.body)) {

			// se il pg colpito viene sconfitto verra "inviato" un messaggio "true"
			if (defender.damaged(striker.getCharacterStats(Character.DAMAGE), power)) {
				defender.death();
				return PlayerActions.KILLED;
			}

			defender.recovery = 8;
			defender.stunned = true;

			return PlayerActions.STUNNED;
		}
		return PlayerActions.MISSED;
	}

	private void setStrikerRecovery(int power, Character pg) {
		// altrimenti si procederà ad impostare il "recupero" dei pg
		// se il colpo sferrato dall'attaccante era forte bisognerà recuperare di più

		if (pg.recovery == 0) {
			if (power == PlayerActions.NORMAL) {
				pg.actual_action = PlayerActions.NORMAL;
				pg.recovery = 10;
			}

			if (power == PlayerActions.HEAVY) {
				pg.actual_action = PlayerActions.HEAVY;
				pg.recovery = 15;
			}
		}

		pg.wait = pg.recovery - 3;
	}

	public void move(int player, int direction) {
		// lo spostamento può avvenire in avanti e all'indietro con 2 limitazioni
		// !non si può uscire dal ring !non si può superare l'avversario anche se lo si
		// può "compenetrare"

		// sposta giocatore 1
		if (player == G1)
			run(pg1, direction);
		// sposta giocatore 2
		else if (player == G2)
			run(pg2, direction);

	}

	public void run(Character pg, int direction) {
		int char_acceleration = 0;

		if (direction == PlayerActions.RETREAT && pg.body.getBegin_pointX() >= 0)
			char_acceleration = -pg.getCharacterStats(Character.SPEED);

		if (direction == PlayerActions.ADVANCE && pg.body.getBegin_pointX() <= ring_size)
			char_acceleration = pg.getCharacterStats(Character.SPEED);

		pg.setAcceleration(char_acceleration);
	}

	public void stop(int player) {
		// ferma giocatore 1
		if (player == G1)
			pg1.acceleration = 0;
		// ferma giocatore 2
		if (player == G2)
			pg2.acceleration = 0;

	}

	// funzione che controlla lo stato generale della battaglia
	public void timeStep() {
		time++;
		if (time % 10 == 0)
			gametime--;

		if (gametime >= 0) {
			if (!pg1.checkStatus())
				winner = G2;
			if (!pg2.checkStatus())
				winner = G1;

			if (!(0 < pg1.body.getBegin_pointX() && pg1.body.getBegin_pointX() < ring_size))
				pg1.setAcceleration(0);

			if (!(0 < pg2.body.getBegin_pointX() && pg2.body.getBegin_pointX() < ring_size))
				pg2.setAcceleration(0);

			return;
		} else {
			gametime = 0;
			int bfull = 400;
			int g1_life = bfull * pg1.getCharacterStats(Character.LIFE)/Character.getCharactersStats(pg1.name)[Character.LIFE];
			int g2_life = bfull * pg2.getCharacterStats(Character.LIFE)/Character.getCharactersStats(pg2.name)[Character.LIFE];
			
			if(g1_life >= g2_life)
				winner = G1;
			else
				winner = G2;
		}
	}

	public Character getG1() {
		return pg1;
	}

	public Character getG2() {
		return pg2;
	}

	public int getWinner() {
		return winner;
	}

	public int getTime() {
		return gametime;
	}
}
