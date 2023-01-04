package app.model;

/**
 * 
 * @author ben
 *
 */
public class Livingroom extends Room {

	private static Livingroom uniqueInstance;
	
	Kitchen kitchen;
	Garden garden;
	Bathroom bathroom;
	Bedroom bedroom;
	
	private Livingroom() {
		super("livingroom");
	}
	
	public static Livingroom getInstance() {
		if ( uniqueInstance == null ) {
			uniqueInstance = new Livingroom();
		}
		return uniqueInstance;
	}
	
	public void init(Kitchen kitchen_instance, Garden garden_instance, Bathroom bathroom_instance, Bedroom bedroom_instance) {
		kitchen = kitchen_instance;
		garden = garden_instance;
		bathroom = bathroom_instance;
		bedroom = bedroom_instance;
		initializationDone();
	}
	
	@Override
	public Room getAdjacent(Room room) {
		
		if ( isInitialized() ) {
			switch( room.getIdName() ) {
				case "kitchen":
					return kitchen;
				case "garden":
					return garden;
				case "bathroom":
					return bathroom;
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
			case "kitchen":
				return true;
			case "garden":
				return true;
			case "bathroom":
				return true;
			case "bedroom":
				return true;
			default:
				return false;
		}
	}
}
