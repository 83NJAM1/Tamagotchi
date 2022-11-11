package app.controller;

/**
 * 
 * @author ben
 * Permet de mettre a jour la vue avec le model
 */
public class Stat {
	
	// ATTENTION: Reference partagé avec model.Pet
	private app.model.Stat model;
	
	// ATTENTION: Reference partagé avec view.Hud
	private app.view.Stat view;
	
	
	public Stat() {
		model = new app.model.Stat();
		view = new app.view.Stat();
	}
	
	public app.model.Stat getModel() {
		return model;
	}
	
	public app.view.Stat getView() {
		return view;
	}
}
