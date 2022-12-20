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

/**
 * 
 * @author ben
 * view.Menu de type StackPane car c'est le conteneur de tout les sous menus
 * il est composé de plusieurs vues qui se superposeront
 */
public class Menu extends StackPane {

	//########################### ATTRIBUTS #####################################
	
	// La vue pour l'affichages et la selection des sauvegardes
	private Load load;
	
	// La vue des options
	private Option option;
	
	// listes des boutons
	private VBox listButtons;
	// Bouton pour revenir dans le jeu
	private Button buttonQuit;
	// Bouton pour revenir dans le jeu
	private Button buttonLoad;
	// Bouton pour revenir dans le jeu
	private Button buttonOpt;
	
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
	
	/**
	 * ActionEvent effectué quand t-on veut afficher la vue Load
	 * déclencheur -> this
	 */
	private EventHandler<ActionEvent> click_new = new EventHandler<ActionEvent>() {
		public void handle(ActionEvent e) {
			load.setLoadAsNew(true);
			load.getValidate().fireEvent(e);
			load.setLoadAsNew(false);
		}
	};
	
	//############################ METHODES #####################################
	
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
		option.setQuitAction(click_quit_opt);
		buttonLoad.setOnAction(click_load);
		load.setQuitAction(click_quit_load);
		buttonNew.setOnAction(click_new);
		
		//initalisations
		updateText();
		updateStyle();
		
		//constructions de la vue
		listButtons.getChildren().addAll(buttonNew, buttonLoad, buttonOpt, buttonQuit);
		this.getChildren().add(listButtons);
	}
	
	/**
	 * Met à jour le texte de tous les élements
	 */
	public void updateText() {
		buttonQuit.setText(App.language.getString("button-quit"));
		buttonNew.setText(App.language.getString("button-newg"));
		buttonLoad.setText(App.language.getString("button-load"));
		buttonOpt.setText(App.language.getString("button-opts"));
		option.updateText();
		load.updateText();
	}
	
	/**
	 * Met à jour le style de tous les élements
	 */
	public void updateStyle() {
								   //top, right, bottom, left
		setMargin(option, new Insets(50., 250. , 50.   , 250.));
		setMargin(load, new Insets(50., 50. , 50.   , 50.));
		setBackground(new Background(new BackgroundFill( Color.WHITESMOKE, null, null) ) );
		listButtons.setSpacing(15);
		listButtons.setAlignment(Pos.CENTER);
	}
	
	/**
	 * L'action agit sur des objets de couche superieur donc elle doit venir de la couche du dessus
	 * @param e l'action-event à appliquer sur le boutton quitter du menu
	 */
	public void setQuitAction(EventHandler<ActionEvent> e) {
		buttonQuit.setOnAction(e);
	}
	
	/**
	 * Permet l'acces à la vue et aux methodes Option sans necessairement passer par des methode de la vue Menu
	 * @return l'instance d'Option
	 */
	public Option getOption() {
		return option;
	}
	
	/**
	 * Permet l'acces à la vue et aux methodes Load sans necessairement passer par des methode de la vue Menu
	 * @return l'instance de Load
	 */
	public Load getLoad() {
		return load;
	}
	
	/**
	 * Permet de masquer la vue Load, utilisé lors d'une validation de chargement
	 */
	public void closeLoad() {
		getChildren().remove(load);
		listButtons.setDisable(false);
	}
}
