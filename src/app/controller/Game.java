package app.controller;

/**
 * 
 * @author ben
 * permet de faire intéragire l'ensemble du jeu avec les actions à associer
 */
public class Game {

	// Model et Vue de Game
	private app.model.Game model;
	private app.view.Game view;
	
	// le pet a manipuler
	private Pet pet;
	
	// il y aura plusieurs room, restont avec une pour le moment
	// TODO choisir une strucutre de donnée pour les stocker
	//      ou on les liste une par une sans structure de donnée
	private Room hall;
	
	
}
