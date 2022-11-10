package app.model.states;
import app.model.*;

/*
 * Etats
 * 
 * Classe abstraite dont les sous-classes sont attachées à un pet et influent sur celui-ci.
 * 
 * Une autre solution aurait été de réunir toutes les fonctions des sous-classes en une énorme fonction dans Pet, afin d'avoir moins de classes
 * Ce n'est pas ce qui a été fait ici. Les sous-classes ont l'avantage de détacher leurs fonctions du reste du modèle pour rendre leur comportement
 * plus clair et plus facilement éditable.
 * 
 * Pour l'instant il y a quelques interactions écrites dans les sous-classes pour l'exemple, mais ce n'est qu'un début.
 */

public abstract class State {
	
	//Valeur de 0 à 1
	protected double value;
	
	public State() {
		//1 est la valeur de départ pour tous les états
		this.value=1;
	}
	
	//Fonction à implémenter pour décrire les interactions de l'état avec le pet et les autres états
	public abstract void pass(Pet pet, double interval);
	
	//Modification de la valeur induite pas pass
	public void change(double d) {
		value+=d;
		contain();
	}
	
	//Maintient de la valeur entre 0 et 1
	public void contain() {
		value=Math.max(Math.min(value, 1), 0);
	}
	
	
	//Booléen informant si l'état est critique
	public boolean isDamaging() {
		return false;
	}
	
	
	//GETTER
	
	public double getValue() {
		return value;
	}
	
}
