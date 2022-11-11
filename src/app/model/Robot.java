package app.model;

/**
 * 
 * @author ben
 * L'animal à prendre soin
 */
public class Robot implements Pet {

	// il y aura plusieurs Stat, restons avec une pour le moment
	// TODO choisir une strucutre de donnée pour les stocker
	//      ou on les liste une par une sans structure de donnée
	// ATTENTION: Reference partagé avec constroller.Stat
	private Stat stats;
	
	public Robot (Stat stats_instances) {
		this.stats = stats_instances;
	}
}