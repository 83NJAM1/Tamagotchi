package app.view;

import javafx.scene.image.Image;

/**
 * 
 * @author ben
 * view.Room est de type Image
 * 
 * pour le moment car si zone cliquable alors le type Image
 * ne sera pas suffisant
 */
public class Room extends Image {

	//########################### ATTRIBUTS #####################################

	//############################ METHODES #####################################
	
	// Constructeur obligatoire car de type Image
	// REMARQUE: nescesaire uniquement pour la vue
	//			 ?? chemin de l'image dans le model ??
	public Room(String imagepath) {
		super(imagepath);
	}

}
