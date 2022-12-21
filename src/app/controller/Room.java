package app.controller;
 
/**
 * 
 * @author ben
 * Permet de mettre a jour la vue avec le roomModel
 */
public class Room {
	
	//########################### ATTRIBUTS #####################################
 
	// ATTENTION: reference partagé avec roomModel.Game 
	private app.model.Room roomModel;
	
	// ATTENTION: reference partagé avec roomView.Game 
	private app.view.Room roomView;
	
	//############################ METHODES #####################################
	
	public Room(String roomName) {
		switch(roomName) {
			case "test":
				roomModel = new app.model.Room("test");
				roomView = new app.view.Room("./res/test_room.png");
				break;
			case "livingroom":
				roomModel = new app.model.Room("livingroom");
				roomView = new app.view.Room("./res/no_image.png");
				break;
			case "kitchen":
				roomModel = new app.model.Room("kitchen");
				roomView = new app.view.Room("./res/no_image.png");
				break;
			case "bathroom":
				roomModel = new app.model.Room("bathroom");
				roomView = new app.view.Room("./res/no_image.png");
				break;
			case "garden":
				roomModel = new app.model.Room("garden");
				roomView = new app.view.Room("./res/no_image.png");
				break;
			default:
				roomModel = new app.model.Room("undefined");
				roomView = new app.view.Room("./res/no_image.png");
				break;
		}
	}
	
	public app.model.Room getModel() {
		return roomModel;
	}
	
	public app.view.Room getView() {
		return roomView;
	}
	
	public void exit() {
		roomModel = null;
		roomView = null;
	}
}
