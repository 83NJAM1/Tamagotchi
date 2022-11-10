package app.view;

import app.model.Pet;
import app.model.Room;
import javafx.scene.canvas.Canvas;

/**
 * 
 * @author ben
 * view.Game est de type Canvas
 * Car toutes les opérations d'affichage concernant le jeu se feront via cette classe
 */
public class Game extends Canvas {
	
	// le view.pet a manipuler
	// ATTENTION reference partagé avec controller.Pet
	private Pet pet;
	
	// il y aura plusieurs room, restont avec une pour le moment
	// TODO choisir une strucutre de donnée pour stocker les Rooms
	//      ou on les liste une par une sans structure de donnée
	// ATTENTION reference partagé avec controller.Room
	private Room hall;
	
	// Barre des actions pour le moment car plus simple a implémenter
	// les futures version pourront implémenter le clique sur objets
	private Action actionBar;
}
