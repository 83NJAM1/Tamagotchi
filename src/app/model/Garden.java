package app.model;

/**
 * 
 * @author ben
 *
 */
public class Garden extends Room {
	
	private static Garden uniqueInstance;
	
	Livingroom livingroom;
	
	private Garden() {
		super("garden");
	}
	
	public static Garden getInstance() {
		if ( uniqueInstance == null ) {
			uniqueInstance = new Garden();
		}
		return uniqueInstance;
	}
	
	public void init(Livingroom livingroom_instance) {
		livingroom = livingroom_instance;
		initializationDone();
	}
	
	@Override
	public Room getAdjacent(Room room) {
		if ( isInitialized() && room.equals(Livingroom.getInstance()) ) {
			return livingroom;
		}
		return this;
	}
	
	@Override
	public boolean isAdjacent(Room room) {
		if ( room.equals(Livingroom.getInstance()) ) {
			return true;
		}
		return false;
	}
}
