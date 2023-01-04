package app.view;
 
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.StackPane;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.event.ActionEvent;

import app.App;
import app.Cleanable;
import app.Localisable;

/**
 * 
 * @author ben
 * view.Menu de type StackPane car c'est le conteneur de tout les sous menus
 * il est composé de plusieurs vues qui se superposeront
 */
public class Menu extends StackPane implements Cleanable, Localisable, Stylable {

	//########################### ATTRIBUTS #####################################
	
	// La vue de chargement
	private Load load;
	// La vue des options
	private Option option;
	// listes des boutons
	private VBox listButtons;
	// Bouton quitter menu
	private Button buttonQuit;
	// Bouton charger partie
	private Button buttonLoad;
	// Bouton configuration
	private Button buttonOpt;
	// Bouton nouvelle partie
	private Button buttonNew;

	//######################### EVENT-ACTION ####################################
	
	/**
	 * ActionEvent effectué quand t-on veut quitter la vue Option
	 * déclencheur -> this
	 */
	private EventHandler<ActionEvent> click_quit_opt = new EventHandler<ActionEvent>() {
		public void handle(ActionEvent e) {
			getChildren().remove(option);
			listButtons.setDisable(false);
		}
	};

	/**
	 * ActionEvent effectué quand t-on veut afficher la vue Option
	 * déclencheur -> this
	 */
	private EventHandler<ActionEvent> click_opt = new EventHandler<ActionEvent>() {
		public void handle(ActionEvent e) {
			getChildren().add(option);
			listButtons.setDisable(true);
		}
	};

	/**
	 * ActionEvent effectué quand t-on veut quitter la vue Load
	 * déclencheur -> this
	 */
	private EventHandler<ActionEvent> click_quit_load = new EventHandler<ActionEvent>() {
		public void handle(ActionEvent e) {
			closeLoad();
		}
	};

	/**
	 * ActionEvent effectué quand t-on veut afficher la vue Load
	 * déclencheur -> this
	 */
	private EventHandler<ActionEvent> click_load = new EventHandler<ActionEvent>() {
		public void handle(ActionEvent e) {
			getChildren().add(load);
			listButtons.setDisable(true);
			load.updateFilesList();
		}
	};
	
	//############################ METHODES #####################################
	
	/**
	 * constructeur
	 */
	public Menu() {
		
		//instanciations
		load = new Load();
		option = new Option();
		listButtons = new VBox();
		buttonQuit = new Button();
		buttonLoad = new Button();
		buttonOpt = new Button();
		buttonNew = new Button();
		
		//assignation action
		buttonOpt.setOnAction(click_opt);
		option.setActionButtonQuit(click_quit_opt);
		buttonLoad.setOnAction(click_load);
		load.setActionButtonCancel(click_quit_load);
		
		//initalisations
		updateText();
		updateStyle();
		
		//constructions de la vue
		listButtons.getChildren().addAll(buttonNew, buttonLoad, buttonOpt, buttonQuit);
		this.getChildren().add(listButtons);
	}
		
	/**
	 * desactive les boutons du menu NOTE évite le déclenchement d'action via le clavier 
	 */
	public void deactivateButton() {
		buttonNew.setDisable(true);
		buttonQuit.setDisable(true);
		buttonLoad.setDisable(true);
		buttonOpt.setDisable(true);
	}
	
	/**
	 * active les boutons du menu
	 */
	public void activateButton() {
		buttonNew.setDisable(false);
		buttonQuit.setDisable(false);
		buttonLoad.setDisable(false);
		buttonOpt.setDisable(false);
	}
	
	/**
	 * définit l'action pour le bouton quitter menu
	 * @param e EventHandler qui gère l'action
	 */
	public void setActionButtonQuit(EventHandler<ActionEvent> e) {
		buttonQuit.setOnAction(e);
	}
	
	/**
	 * définit l'action pour le bouton nouvelle partie
	 * @param e EventHandler qui gère l'action
	 */
	public void setActionButtonNew(EventHandler<ActionEvent> e) {
		buttonNew.setOnAction(e);
	}
	
	/**
	 * obtient la vue enfant Option
	 * @return l'instance de Option
	 */
	public Option getViewOption() {
		return option;
	}
	
	/**
	 * obtient la vue enfant Load
	 * @return l'instance de Load
	 */
	public Load getViewLoad() {
		return load;
	}
	
	/**
	 * Permet de masquer la vue Load, utilisé lors d'une validation de chargement
	 */
	public void closeLoad() {
		getChildren().remove(load);
		listButtons.setDisable(false);
	}
	
	@Override
	public void updateStyle() {
								   //top, right, bottom, left
		setMargin(option, new Insets(50., 250. , 50.   , 250.));
		setMargin(load, new Insets(50., 50. , 50.   , 50.));
		setBackground(new Background(new BackgroundFill( Color.WHITESMOKE, null, null) ) );
		listButtons.setSpacing(15);
		listButtons.setAlignment(Pos.CENTER);
	}
	
	@Override
	public void updateText() {
		buttonQuit.setText(App.getString("button-quit"));
		buttonNew.setText(App.getString("button-newg"));
		buttonLoad.setText(App.getString("button-load"));
		buttonOpt.setText(App.getString("button-opts"));
		option.updateText();
		load.updateText();
	}
	
	@Override
	public void clean() {
		
		if ( option != null ) {
			option.clean();
			option = null;
		}
		
		if ( load != null ) {
			load.clean();
			load = null;
		}
		
		listButtons = null;
		buttonQuit = null;
		buttonLoad = null;
		buttonOpt = null;
		buttonNew = null;
	}
}
