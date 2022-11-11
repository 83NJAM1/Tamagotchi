package app.view;

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
	
	public Main(Game game_instance, Menu menu_instance) {
		this.game = game_instance;
		this.menu = menu_instance;
	}
}
