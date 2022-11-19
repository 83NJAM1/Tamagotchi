package app.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 * 
 * @author ben
 * Controller principale encapsulant tout
 * il fait le lien avec le thread javafx
 */
public class Main {
	
	//########################### ATTRIBUTS #####################################
	
	private app.view.Main view;
	
	private Menu menu;
	private Game game;
	
	//######################### EVENT-ACTION ####################################
	
	/**
	 * ActionEvent effectué quand t-on veut charger une partie
	 * déclencheur -> c.Menu -> v.Menu -> v.Load
	 */
	private EventHandler<ActionEvent> load_file = new EventHandler<ActionEvent>() {
		public void handle(ActionEvent e) {
			System.out.println("Loading... : " + menu.getView().getLoad().getChoosenSave());
			menu.getView().closeLoad();
		}
	};
	
	//############################ METHODES #####################################

	public Main() {
		menu = new Menu();
		game = new Game();
		view = new app.view.Main( game.getView(), menu.getView() );
		
		menu.getView().getLoad().setValidateAction(load_file);
	}
	
	public app.view.Main getView() {
		return view;
	}
	
	public Menu getMenu() {
		return menu;
	}
	
	public Game getGame() {
		return game;
	}
}
