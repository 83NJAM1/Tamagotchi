package app.view;

import app.App;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.layout.StackPane;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.event.ActionEvent;

/**
 * 
 * @author ben
 * view.Menu de type StackPane car c'est le conteneur de tout les sous menus
 * il est composé de plusieurs vues qui se superposeront
 */
public class Menu extends StackPane {

	// La vue pour customiser le pet
	private CustomPet customPet;
	
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
	
	
	private EventHandler<ActionEvent> click_quit_opt = new EventHandler<ActionEvent>() {
		public void handle(ActionEvent e) {
			getChildren().remove(option);
		}
	};
	private EventHandler<ActionEvent> click_opt = new EventHandler<ActionEvent>() {
		public void handle(ActionEvent e) {
			getChildren().add(option);
		}
	};
	private EventHandler<ActionEvent> click_quit_load = new EventHandler<ActionEvent>() {
		public void handle(ActionEvent e) {
			getChildren().remove(load);
		}
	};
	private EventHandler<ActionEvent> click_load = new EventHandler<ActionEvent>() {
		public void handle(ActionEvent e) {
			getChildren().add(load);
		}
	};
	
	public Menu() {
		
		customPet = new CustomPet();
		load = new Load();
		option = new Option();
		
		listButtons = new VBox(15);
		listButtons.setAlignment(Pos.CENTER);
		buttonQuit = new Button();
		buttonLoad = new Button();
		buttonOpt = new Button();
		
		buttonOpt.setOnAction(click_opt);
		
		option.setQuitAction(click_quit_opt);
		
		updateText();
		updateBuild();
	}
	
	/**
	 * Toutes les vues devrons avoir cette methodes car necessaire lors du changement de langue
	 */
	public void updateText() {
		buttonQuit.setText(App.language.getString("menu-button-quit"));
		buttonLoad.setText(App.language.getString("menu-button-load"));
		buttonOpt.setText(App.language.getString("menu-button-opts"));
		option.updateText();
	}
	
	public void updateBuild() {
		//double top, double right, double bottom, double left
		setMargin(option, new Insets(50., 250., 50., 250.));
		listButtons.getChildren().addAll(buttonLoad, buttonOpt, buttonQuit);
		this.getChildren().add(listButtons);
	}
	
	public void setQuitAction(EventHandler<ActionEvent> e) {
		buttonQuit.setOnAction(e);
	}
	
	public Option getOption() {
		return option;
	}
}
