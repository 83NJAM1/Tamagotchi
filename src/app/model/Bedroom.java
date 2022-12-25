package app.model;

public class Bedroom extends Room {

	private static Bedroom uniqueInstance;
	
	Livingroom livingroom;
	Bathroom bathroom;
	
	private Bedroom() {
		super("bedroom");
	}
	
	public static Bedroom getInstance() {
		if ( uniqueInstance == null ) {
			uniqueInstance = new Bedroom();
		}
		return uniqueInstance;
	}
	
	public void init(Livingroom livingroom_instance, Bathroom bathroom_instance) {
		livingroom = livingroom_instance;
		bathroom = bathroom_instance;
		initializationDone();
	}
	
	@Override
	public Room getAdjacent(Room room) {
		
		if ( isInitialized() ) {
			switch( room.getIdName() ) {
				case "livingroom":
					return livingroom;
				case "bathroom":
					return bathroom;
				default:
					return this;
			}
		}
		
		return this;
	}
	
	@Override
	public boolean isAdjacent(Room room) {
		switch( room.getIdName() ) {
			case "livingroom":
				return true;
			case "bathroom":
				return true;
			default:
				return false;
		}
	}
}
