package test;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class EraserButton extends MyButton {

	public EraserButton (String name) {
		super(name);

		action = new EventHandler<ActionEvent>() {
		    @Override
		    public void handle(ActionEvent action) {
		        System.out.println("eraser clicked");
		        ((MyCanvas)target.get(0)).setPen(null);
		    }
		};
		
		this.setOnAction(action);
	}
	
}
