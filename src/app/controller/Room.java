package app.controller;
 
/**
 * 
 * @author ben
 * Permet de mettre a jour la vue avec le model
 */
public class Room {
	
	//########################### ATTRIBUTS #####################################
 
	// ATTENTION: reference partagé avec model.Game 
	private app.model.Room model;
	
	// ATTENTION: reference partagé avec view.Game 
	private app.view.Room view;
	
	//############################ METHODES #####################################
	
	public Room(String roomName) {
		switch(roomName) {
			case "test":
				model = new app.model.Room("test");
				view = new app.view.Room("./res/test_room.png");
				break;
			case "livingroom":
				model = new app.model.Room("livingroom");
				view = new app.view.Room("./res/no_image.png");
				break;
			case "kitchen":
				model = new app.model.Room("kitchen");
				view = new app.view.Room("./res/no_image.png");
				break;
			case "bathroom":
				model = new app.model.Room("bathroom");
				view = new app.view.Room("./res/no_image.png");
				break;
			case "garden":
				model = new app.model.Room("garden");
				view = new app.view.Room("./res/no_image.png");
				break;
			default:
				model = new app.model.Room("undefined");
				view = new app.view.Room("./res/no_image.png");
				break;
		}
	}
	
	public app.model.Room getModel() {
		return model;
	}
	
	public app.view.Room getView() {
		return view;
	}
	
	public void exit() {
		model = null;
		view = null;
	}
}
