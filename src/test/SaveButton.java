package test;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class SaveButton extends MyButton {
	
	public SaveButton (String name, String pathfile) {
		super(name);
		
		action = new EventHandler<ActionEvent>() {
		    @Override
		    public void handle(ActionEvent action) {
		        System.out.println("saved as " + pathfile);
		        ((MyCanvas)target.get(0)).save(pathfile);
		    }
		};
		
		this.setOnAction(action);
	}
}
