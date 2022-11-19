package app.view;

/**
 * 
 * @author ben
 * affiche les stats ainsi que les actions
 */
public class Hud {
	
	//########################### ATTRIBUTS #####################################

	// Barre des actions pour le moment car plus simple a implémenter
	// les futures version pourront implémenter le clique sur objets
	private Action actionBar;
	
	// il y aura plusieurs Stat, restons avec une pour le moment
	// TODO choisir une strucutre de donnée pour les stocker
	//      ou on les liste une par une sans structure de donnée
	// ATTENTION: reference partagé avec controller.Stat
	private Stat stats;
	
	//############################ METHODES #####################################
	
	public Hud(Stat stats_instances) {
		this.actionBar = new Action();
		this.stats = stats_instances;
	}
}
