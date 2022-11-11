package app.controller;

/**
 * 
 * @author ben
 * Permet de mettre a jour la vue avec le model
 */
public class Room {

	// Model et Vue de Room
	
	// ATTENTION: reference partagé avec model.Game 
	private app.model.Room model;
	
	// ATTENTION: reference partagé avec view.Game 
	private app.view.Room view;
}
