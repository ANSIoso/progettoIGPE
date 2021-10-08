package application.model;

public class PlayerActions {
	public static final int NOTHING = -1;
	
	// movements
	public static final int ADVANCE = 0;
	public static final int RETREAT = 1;
	
	// attacks
	public static final int NORMAL = 2;
	public static final int HEAVY  = 3;
	
	//actions not chosen by the user
	public static final int IDLE    = 4;
	public static final int TAKEHIT = 5;
	public static final int DEATH   = 6;
	
	
	// risultati attacco
	public static final byte STUNNED = 1;
	public static final byte MISSED  = 2;
	public static final byte KILLED  = 3;
}
