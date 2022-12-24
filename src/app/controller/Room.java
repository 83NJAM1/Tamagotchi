package app.controller;

import app.Reinstanciable;

/**
 * 
 * @author ben
 * Permet de mettre a jour la vue avec le roomModel
 */
public class Room implements Reinstanciable {
	
	//########################### ATTRIBUTS #####################################
 
	// donées de la pièce
	private app.model.Room roomModel; //NOTE: reference partagé avec roomModel.Game 
	
	// affichage de la pièce
	private app.view.Room roomView; //NOTE: reference partagé avec roomView.Game 
	
	//############################ METHODES #####################################
	
	/**
	 * constructeur
	 * @param roomName la chaîne de charactère identifiant la pièce.
	 */
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
	
	/**
	 * obtient le model Room
	 * @return Room, le model
	 */
	public app.model.Room getModel() {
		return roomModel;
	}
	
	/**
	 * obtient la vue Room
	 * @return Room, la vue
	 */
	public app.view.Room getView() {
		return roomView;
	}
		
	@Override
	public void exit() {
		roomModel = null;
		
		roomView.exit();
		roomView = null;
	}
}
