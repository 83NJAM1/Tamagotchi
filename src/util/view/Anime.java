package util.view;

import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.event.EventHandler;
import javafx.scene.Node;

public class Anime extends Node{

	private String id;
	private FrameList frames;

	public Anime(String id) {
		this.id = id;
		frames = new FrameList();
	}
	
	public String getNameId() {
		return id;
	}
	
	public void addFrame(Frame f) {
		frames.addItem(f);
	}
	public void removeFrame(int i) {
		frames.removeItem(-1);
	}
	public void addFrame(Frame ... f) {
		frames.addItems(f);
	}
	
	// ########################### CELLULE D'UNE LISTE ###############################
	
	static class AnimeCell extends ListCell<Anime> {
		HBox layout;
		Label text;
		
		public AnimeCell() {
			super();
			
			layout = new HBox();
			text = new Label();
			
			layout.getChildren().add(text);
		}
		
		public void updateItem(Anime a, boolean empty) {
			super.updateItem(a, empty);
			
			if(!empty) {
				text.setText(a.id);
				this.setGraphic(layout);
			}
			else {
				text.setText("");
				this.setGraphic(null);
			}
		}
		
	}
}
