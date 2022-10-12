package util.view;

import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.scene.text.TextAlignment;

public class Frame extends HBox {
	
	String spritesheet;
	long time;
	
	public Frame(String spritesheet, long time) {
		this.spritesheet = spritesheet;
		this.time = time;
	}
	
	// ########################### CELLULE D'UNE LISTE ###############################

	static class FrameCell extends ListCell<Frame> {
		HBox layout;
		Label textPath;
		Label textTime;
		
		public FrameCell() {
			super();
			
			layout = new HBox(10);
			textPath = new Label();
			textTime = new Label();
			
			layout.getChildren().add(textPath);
			layout.getChildren().add(textTime);
		}
		
		public void updateItem(Frame f, boolean empty) {
			super.updateItem(f, empty);
			
			if(!empty) {
				textPath.setText(f.spritesheet);
				textTime.setText(""+f.time);
				this.setGraphic(layout);
			}			
			else {
				textPath.setText("");
				textTime.setText("");
				this.setGraphic(null);
			}
		}
		
	}
}
