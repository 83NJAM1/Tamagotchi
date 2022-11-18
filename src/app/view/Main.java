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

	//ATTENTION: reference partagé avec controller.Game
	private Game game;
	
	//ATTENTION: reference partagé avec controller.Menu
	private Menu menu;
	
	private EventHandler<ActionEvent> click_quit_menu = new EventHandler<ActionEvent>() {
		public void handle(ActionEvent e) {
			//getChildren().remove(menu);
			menu.toBack();
		}
	};
	private EventHandler<ActionEvent> click_open_menu = new EventHandler<ActionEvent>() {
		public void handle(ActionEvent e) {
			//getChildren().add(menu);
			menu.toFront();
		}
	};
	
	public Main(Game game_instance, Menu menu_instance) {
		this.game = game_instance;
		this.menu = menu_instance;
		
		menu.setQuitAction(click_quit_menu);
		
		this.getChildren().addAll(game, menu);
	}
}
