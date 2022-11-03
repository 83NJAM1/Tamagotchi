package app.view;

import javafx.scene.control.Button;
import javafx.event.*;
import app.controller.Controler;


public class Bouton extends Button {

	
	public Bouton(String text,String eventCode,int context) {
		
		super(text);
		
		this.setOnAction(new EventHandler<ActionEvent>() {

		    @Override
		    public void handle(ActionEvent event) {
		    	if(context!=-1) Controler.contextChange(context);
		        Controler.boutonClicked(eventCode);
		    }
		});
		
		
	}
	
	public void setPosition(double top, double left) {
		setTranslateY(top);
		setTranslateX(left);
	}
	


}
