package test;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.paint.Color;

public class PenColorButton extends MyButton {
	
	Color color;
	
	public PenColorButton (String name, Color color) {
		super(name);
		
		this.color = color;
		
		action = new EventHandler<ActionEvent>() {
		    @Override
		    public void handle(ActionEvent action) {
		        System.out.println("red clicked");
		        ((MyCanvas)target.get(0)).setPen(color);
		    }
		};
		
		this.setOnAction(action);
	}
	
}
