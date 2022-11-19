package app.view;

import javafx.scene.image.Image;

/**
 * 
 * @author ben
 * view.Pet permet l'affichage du pet dans un etat donné par model.Pet
 * TODO une classe Sprite
 *      Ca me semble mieux de faire une classe Sprite et que view.Pet
 *      hérite de Sprite
 */
public class Pet extends Image {

	//########################### ATTRIBUTS #####################################

	//############################ METHODES #####################################
	
	// Constructeur obligatoire car de type Image
	// REMARQUE: nescesaire uniquement pour la vue
	//			 ?? chemin de l'image dans le model ??
	public Pet(String spritesheet) {
		super(spritesheet);
	}

}
