package app.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.event.Event;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;

import app.Componable;
import app.Localisable;

/**
 * 
 * @author ben
 * permet d'interchanger les vues Game, Menu et CustomPet 
 */
public class Main extends StackPane implements Componable, Localisable {

	//########################### ATTRIBUTS #####################################
	public static final String GAMEIMAGEPATH = "res/game/images/"; 
	public static final String IUIMAGEPATH = "res/interface/images/";
	
	// la vue du jeu
	private Game game; //NOTE: reference partagé avec controller.Game
	
	// la vue du menu
	private Menu menu; //NOTE: reference partagé avec controller.Menu
	
	// La vue de customisation du pet
	private CustomPet customPet;
	boolean menu_hiden;
	//######################### EVENT-ACTION ####################################
	
	/**
	 * ActionEvent effectué quand t-on veut masquer le Menu
	 * déclencheur -> this -> v.Menu
	 */
	private EventHandler<KeyEvent> key_menu = new EventHandler<KeyEvent>() {
		
		public void handle(KeyEvent e) {
			if ( e.getCode() == KeyCode.ESCAPE ) {
				
				if (menu_hiden)
					showMenu();
				else
					hideMenu();
			}
			System.out.println(e.getCode() + ":" + ((menu_hiden) ? "out menu" : "in menu"));
		}
	};
	
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
	
	/**
	 * constructeur charger/continuer partie
	 * @param game_instance l'instance du jeu
	 * @param menu_instance l'instance du menu
	 */
	public Main(Game game_instance, Menu menu_instance) {
		init(game_instance, menu_instance);
	}
	
	/**
	 * constructeur nouvelle partie
	 * @param menu_instance l'instance du menu
	 */
	public Main(Menu menu_instance) {
		
		customPet = new CustomPet();
		
		menu = menu_instance;
		
		getChildren().add(customPet);
	}
	
	/**
	 * initialisation
	 * @param game_instance l'instance du jeu
	 * @param menu_instance l'instance du menu
	 */
	private void init(Game game_instance, Menu menu_instance) {

		game = game_instance;
		menu = menu_instance;
		
		menu.setActionButtonQuit(click_quit_menu);
		game.getViewHud().getChildAction().setActionButtonMenu(click_open_menu);
		
		menu_hiden = true;
		this.addEventHandler(KeyEvent.KEY_RELEASED, key_menu);
		hideMenu();
		getChildren().add(menu);
		getChildren().add(game);
	}
	
	/**
	 * initialisation en enlevant la vue de customisation
	 * @param game_instance l'instance du jeu
	 */
	public void init(Game game_instance) {
		
		init(game_instance, menu);

		getChildren().remove(customPet);
		customPet.exit();
		customPet = null;
	}
	
	/**
	 * obtient la vue de customisation
	 * @return customPet, la vue
	 */
	public CustomPet getChildCustomPet() {
		return customPet;
	}
	
    /**
     * affiche la vue de customisation
     */
	public void showCustom() {
		game.setDisable(true);
		menu.setDisable(true);
		customPet.setDisable(false);
		customPet.toFront();
	}
	
    /**
     * masque la vue de customisation
     */
	public void hideCustom() {
		game.setDisable(false);
		menu.setDisable(false);
		customPet.setDisable(true);
		customPet.toBack();
	}
	
    /**
     * affiche la vue du menu
     */
	public void showMenu() {
		menu_hiden = false;
		game.setDisable(true);
		menu.activateButton();
		menu.toFront();
	}
	
    /**
     * masque la vue du menu
     */
	public void hideMenu() {
		menu_hiden = true;
		game.setDisable(false);
		menu.deactivateButton();
		menu.toBack();
	}
	
	@Override
	public void updateText() {
		game.updateText();
		menu.updateText();
	}
	
	@Override
	public void exit() {
		menu.exit();
		if ( game != null ) {
			game.exit();
		}
		if ( customPet != null ) {
			customPet.exit();
		}
		menu = null;
		game = null;
		customPet = null;
	}
}
