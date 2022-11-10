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

	// Constructeur obligatoire car de type Image
	// EVIDENCE: nescesaire uniquement pour la vue
	public Room(String imagepath) {
		super(imagepath);
	}

}
