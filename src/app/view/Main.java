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
	
	// La vue pour customiser le pet
	private CustomPet customPet;
	
	//######################### EVENT-ACTION ####################################
	
	/**
	 * ActionEvent effectué quand t-on veut masquer le Menu
	 * déclencheur -> this -> v.Menu
	 */
	private EventHandler<ActionEvent> click_quit_menu = new EventHandler<ActionEvent>() {
		public void handle(ActionEvent e) {
			hideMenu();
		}
	};

	/**
	 * ActionEvent effectué quand t-on veut afficher le Menu
	 * déclencheur -> this -> v.Game -> v.Action
	 */
	private EventHandler<ActionEvent> click_open_menu = new EventHandler<ActionEvent>() {
		public void handle(ActionEvent e) {
			showMenu();
		}
	};
	
	//############################ METHODES #####################################
	
	public Main(Game game_instance, Menu menu_instance) {
		initNoCustomPet(game_instance, menu_instance);
	}
	
	public Main() {
		customPet = new CustomPet();
		this.getChildren().add(customPet);
	}
	
	private void initNoCustomPet(Game game_instance, Menu menu_instance) {
		this.game = game_instance;
		this.menu = menu_instance;
		
		menu.setActionButtonQuit(click_quit_menu);
		game.getChildHud().getChildAction().setActionButtonMenu(click_open_menu);
		
		this.getChildren().add(menu);
		this.getChildren().add(game);
	}
	
	public void initWithCustomPet(Game game_instance, Menu menu_instance) {
		this.getChildren().remove(customPet);
		initNoCustomPet(game_instance, menu_instance);
	}
	
	public CustomPet getCustomPet() {
		return customPet;
	}
	
	public void showCustom() {
		game.setDisable(true);
		menu.setDisable(true);
		customPet.toFront();
	}
	public void hideCustom() {
		game.setDisable(false);
		menu.setDisable(false);
		customPet.toBack();
	}
	
	public void showMenu() {
		game.setDisable(true);
		menu.toFront();
	}
	public void hideMenu() {
		game.setDisable(false);
		menu.toBack();
	}
	
	public void exit() {
		menu.exit();
		game.exit();
		if ( customPet != null ) {
			customPet.exit();
		}
		menu = null;
		game = null;
		customPet = null;
	}
}
