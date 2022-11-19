package app.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.StackPane;

/**
 * 
 * @author ben
 * view.Main permet d'interchanger les vues Game et Menu 
 */
public class Main extends StackPane {

	//########################### ATTRIBUTS #####################################

	//ATTENTION: reference partagé avec controller.Game
	private Game game;
	
	//ATTENTION: reference partagé avec controller.Menu
	private Menu menu;
	
	//######################### EVENT-ACTION ####################################
	
	/**
	 * ActionEvent effectué quand t-on veut masquer le Menu
	 * déclencheur -> this -> v.Menu
	 */
	private EventHandler<ActionEvent> click_quit_menu = new EventHandler<ActionEvent>() {
		public void handle(ActionEvent e) {
			//getChildren().remove(menu);
			menu.toBack();
		}
	};

	/**
	 * ActionEvent effectué quand t-on veut afficher le Menu
	 * déclencheur -> this -> v.Game
	 */
	private EventHandler<ActionEvent> click_open_menu = new EventHandler<ActionEvent>() {
		public void handle(ActionEvent e) {
			//getChildren().add(menu);
			menu.toFront();
		}
	};
	
	//############################ METHODES #####################################
	
	public Main(Game game_instance, Menu menu_instance) {
		this.game = game_instance;
		this.menu = menu_instance;
		
		menu.setQuitAction(click_quit_menu);
		
		this.getChildren().addAll(game, menu);
	}
}
