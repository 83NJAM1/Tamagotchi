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
	
	public Room() {
		model = new app.model.Room("test");
		view = new app.view.Room("./res/test_room.png");
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
