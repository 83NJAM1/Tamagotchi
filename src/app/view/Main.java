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
			game.setDisable(false);
			menu.toBack();
		}
	};

	/**
	 * ActionEvent effectué quand t-on veut afficher le Menu
	 * déclencheur -> this -> v.Game -> v.Action
	 */
	private EventHandler<ActionEvent> click_open_menu = new EventHandler<ActionEvent>() {
		public void handle(ActionEvent e) {
			//getChildren().add(menu);
			game.setDisable(true);
			menu.toFront();
		}
	};
	
	//############################ METHODES #####################################
	
	public Main(Game game_instance, Menu menu_instance) {
		this.game = game_instance;
		this.menu = menu_instance;
		
		menu.setQuitAction(click_quit_menu);
		game.getActionBar().setActionMenu(click_open_menu);
		
		this.getChildren().add(menu);
		this.getChildren().add(game);
	}
	
	public void showMenu() {
		game.setDisable(true);
		menu.toFront();
	}
	public void hideMenu() {
		game.setDisable(false);
		menu.toBack();
	}
}
