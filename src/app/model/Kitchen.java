package app.model;

public class Kitchen extends Room {

	private static Kitchen uniqueInstance;
	
	Livingroom livingroom;
	
	private Kitchen() {
		super("kitchen");
	}
	
	public static Kitchen getInstance() {
		if ( uniqueInstance == null ) {
			uniqueInstance = new Kitchen();
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
