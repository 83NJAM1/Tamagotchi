package app.controller;

/**
 * 
 * @author ben
 * Permet de mettre a jour la vue avec le model
 */
public class Pet {

	// Model et Vue de Pet
	private app.model.Pet model;
	// ATTENTION: référence partagé avec view.Game
	private app.view.Pet view;
	
	// il y aura plusieurs Stat, restont avec une pour le moment
	// TODO choisir une strucutre de donnée pour les stocker
	//      ou on les liste une par une sans structure de donnée
	private Stat test;
}
