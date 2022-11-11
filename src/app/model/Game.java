package app.model;

/**
 * 
 * @author ben
 * Encapsule tous les élements du jeu
 */
public class Game {
	
	// le pet a manipuler
	// ATTENTION: référence partagé avec controller.Pet
	private Pet pet;
	
	// il y aura plusieurs Room, restons avec une pour le moment
	// TODO choisir une strucutre de donnée pour stocker les Rooms
	//      ou on les liste une par une sans structure de donnée
	// ATTENTION: référence partagé avec controller.Room
	private Room hall;
}
