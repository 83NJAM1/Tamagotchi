package app.controller;

/**
 * 
 * @author ben
 * Permet de mettre a jour la vue avec le model
 */
public class Stat {

	// Model et Vue de Stat
	
	// ATTENTION: Reference partagé avec model.Pet
	private app.model.Stat model;
	
	// ATTENTION: Reference partagé avec view.HUD
	private app.view.Stat view;
}
