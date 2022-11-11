package app.controller;

/**
 * 
 * @author ben
 * Controller principale encapsulant tout
 * il fait le lien avec le thread javafx
 */
public class Main {
	
	// juste pour le teste je l'ai instancié ici
	// TODO constructeur, action, ordonnancement, etc.
	private app.view.Main view = new app.view.Main();
	
	private Menu menu;
	private Game game;
	
	/**
	 * permet au thread javafx d'y acceder
	 * @return la vue principale
	 */
	public app.view.Main getView() {
		return view;
	}
}
