package app.controller;

/**
 * 
 * @author ben
 * Permet de mettre a jour la vue avec le model
 */
public class Pet {
	
	// ATTENTION: référence partagé avec model.Game
	private app.model.Pet model;
	
	// ATTENTION: référence partagé avec view.Game
	private app.view.Pet view;
	
	// il y aura plusieurs Stat, restons avec une pour le moment
	// TODO choisir une strucutre de donnée pour les stocker
	//      ou on les liste une par une sans structure de donnée
	private Stat stats;
	
	public Pet() {
		stats = new Stat();
		model = new app.model.Robot( stats.getModel() );
		view = new app.view.Pet("./res/test_pet.png");
	}
	
	public app.model.Pet getModel() {
		return model;
	}
	
	public app.view.Pet getView() {
		return view;
	}
	
	public Stat getStats() {
		return stats;
	}
}
