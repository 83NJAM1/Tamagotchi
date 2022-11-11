package app.controller;

/**
 * 
 * @author ben
 * Controller principale encapsulant tout
 * il fait le lien avec le thread javafx
 */
public class Main {
	
	// TODO constructeur, action, ordonnancement, etc.
	
	private app.view.Main view;
	
	private Menu menu;
	private Game game;
	
	/**
	 * permet au thread javafx d'y acceder
	 * @return la vue principale
	 */
	public app.view.Main getView() {
		return view;
	}
	
	// juste pour les testes je les ai instancié
	public Main() {
		menu = new Menu();
		game = new Game();
		view = new app.view.Main( game.getView(), menu.getView() );
	}
}
