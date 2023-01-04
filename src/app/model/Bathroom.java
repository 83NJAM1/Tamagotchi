package app.model;

/**
 * 
 * @author ben
 *
 */
public class Bathroom extends Room {
	
	private static Bathroom uniqueInstance;
	
	Livingroom livingroom;
	Bedroom bedroom;
	
	private Bathroom() {
		super("bathroom");
	}
	
	public static Bathroom getInstance() {
		if ( uniqueInstance == null ) {
			uniqueInstance = new Bathroom();
		}
		return uniqueInstance;
	}
	
	public void init(Livingroom livingroom_instance, Bedroom bedroom_instance) {
		livingroom = livingroom_instance;
		bedroom = bedroom_instance;
		initializationDone();
	}
	
	@Override
	public Room getAdjacent(Room room) {
		
		if ( isInitialized() ) { 
			switch( room.getIdName() ) {
				case "livingroom":
					return livingroom;
				case "bedroom":
					return bedroom;
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
			case "bedroom":
				return true;
			default:
				return false;
		}
	}
}
