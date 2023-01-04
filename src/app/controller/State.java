package app.controller;

import app.Cleanable;
import app.Localisable;

/**
 * 
 * @author ben
 * Permet de mettre a jour la vue avec le statModel
 */
public class State implements Cleanable, Localisable {
	
	//########################### ATTRIBUTS #####################################
 
	// données de l'état
	private app.model.State stateModel; //NOTE: Reference partagé avec model.Pet
	
	// affichage de l'état
	private app.view.State stateView; //NOTE: Reference partagé avec view.Hud + view.Pet
    
	//############################ METHODES #####################################
	
    /**
     * constructeur
     * @param name la chaîne de charactère identifiant l'état
     */
	public State(String key) {
		stateModel = new app.model.State(key);
		stateView = new app.view.State(key);
	}
	
	/**
	 * change la valeur de l'état
	 * @param value la valeur souhaité
	 */
	public void setValue(Double value) {
		stateModel.setValue(value);
		stateView.changeValue(value);
	}
	
	/**
	 * met a jour la vue via le model
	 */
	public void updateValue() {
		stateView.changeValue(stateModel.getValue());
	}
	
	/**
	 * obtient le model State
	 * @return State, le model
	 */
	public app.model.State getModel() {
		return stateModel;
	}
	
	/**
	 * obtient la vue State
	 * @return State, la vue
	 */
	public app.view.State getView() {
		return stateView;
	}
	
	@Override
	public void updateText() {
		stateView.updateText();
	}
	
	@Override
	public void clean() {
	
		stateModel = null;
		
		stateView.clean();
		stateView = null;
	}
}
