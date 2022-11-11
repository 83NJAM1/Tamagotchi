package app.view;

import javafx.scene.layout.StackPane;

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
}
