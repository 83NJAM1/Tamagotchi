package app.controller;

import app.Componable;

/**
 * 
 * @author ben
 * Permet de mettre a jour la vue avec le roomModel
 */
public class Room implements Componable {
	
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
			case "livingroom":
				roomModel = app.model.Livingroom.getInstance();
				roomView = new app.view.Room("./res/no_image.png");
				break;
			case "kitchen":
				roomModel = app.model.Kitchen.getInstance();
				roomView = new app.view.Room("./res/no_image.png");
				break;
			case "bathroom":
				roomModel = app.model.Bathroom.getInstance();
				roomView = new app.view.Room("./res/no_image.png");
				break;
			case "garden":
				roomModel = app.model.Garden.getInstance();
				roomView = new app.view.Room("./res/no_image.png");
				break;
			case "bedroom":
				roomModel = app.model.Bedroom.getInstance();
				roomView = new app.view.Room("./res/no_image.png");
				break;
			default:
				roomModel = app.model.Livingroom.getInstance();
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
