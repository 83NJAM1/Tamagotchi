package app.view;

import javafx.scene.canvas.Canvas;

/**
 * 
 * @author ben
 * view.Game est de type Canvas|StakPane
 * TODO determiner si Canvas ou StakPane.
 *      j'ai choisi Canvas mais ça pourrait poser 
 *      probleme si l'on veut superposer d'autre vue
 * Permet des opérations d'affichage concernant le jeu
 */
public class Game extends Canvas {
	
	// le view.pet a manipuler
	// ATTENTION reference partagé avec controller.Pet
	private Pet pet;
	
	// il y aura plusieurs room, restons avec une pour le moment
	// TODO choisir une strucutre de donnée pour stocker les Rooms
	//      ou on les liste une par une sans structure de donnée
	// ATTENTION reference partagé avec controller.Room
	private Room hall;
	
	private Hud hud;
	
	public Game(Pet pet_instance, Room room_instance, Stat stats_instances) {
		this.pet = pet_instance;
		this.hall = room_instance;
		this.hud = new Hud(stats_instances);
	}
}
