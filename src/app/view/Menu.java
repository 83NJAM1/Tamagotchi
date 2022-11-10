package app.view;

import javafx.scene.layout.AnchorPane;

/**
 * 
 * @author ben
 * view.Menu de type AnchorPane car c'est le conteneur de tout les boutons
 * il est composé de plusieurs vues impliquant différentes actions
 */
public class Menu extends AnchorPane {

	// La vue pour customiser le pet
	CustomPet customPet;
	
	// La vue pour l'affichages et la selection des sauvegardes
	Load load;
	
	// La vue des options
	Option option;
}
