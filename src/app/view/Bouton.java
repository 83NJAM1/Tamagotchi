package app.view;

import javafx.scene.control.Button;
import javafx.event.*;
import app.controller.Controler;

/*
 * Premier jet de bouton interactif
 */

public class Bouton extends Button {

	
	public Bouton(String text,String eventCode,int context) {
		
		super(text);
		
		//Evenement déclenché lors d'une interaction avec le bouton
		this.setOnAction(new EventHandler<ActionEvent>() {

		    @Override
		    public void handle(ActionEvent event) {
		    	//Modification du contexte, si opportune
		    	if(context!=-1) Controler.contextChange(context);
		    	//Signalement au contrôleur que quelque chose s'est passé
		        Controler.boutonClicked(eventCode);
		    }
		});
		
		
	}
	
	//Système primitif de coordonnées
	public void setPosition(double top, double left) {
		setTranslateY(top);
		setTranslateX(left);
	}
	


}
