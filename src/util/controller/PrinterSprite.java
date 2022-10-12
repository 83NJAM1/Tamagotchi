package util.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.BoundingBox;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;

public class PrinterSprite {
	private util.view.PrinterSprite view;

	private Sprite sprite;
	boolean paused = true;
	
	EventHandler<ActionEvent> pauseAction = new EventHandler<ActionEvent>() {
		
		public void handle(ActionEvent e) {
			if (!paused) {
				sprite.pause();
				view.getPauseButton().setText("  ▶️\nplay");
			}
			else {
				sprite.play();
				view.getPauseButton().setText("   ⏸️\npause");
			}
			paused=!paused;
			System.out.println("paused:"+paused);
		}
	};
	
	public PrinterSprite(double w, double h) {
		view = new util.view.PrinterSprite(w, h, pauseAction);
	}
	
	public void blank() {
		view.blank();
	}
	
	public void draw() {
		view.draw(sprite.getSourceBox(), sprite.getDestBox());
	}
	
	public void draw(Sprite s, boolean center) {
		if (s.getImage() != null) {
			view.draw(s.getImage(), s.getSourceBox(), s.getDestBox(), center);
		}
		else {
			System.out.println("IMAGE NULL");
		}
	}
	
	public void setSprite(Sprite sprite) {
		this.sprite = sprite;
		view.setSpriteSheet(sprite.getSpriteSheet());
	}
	
	public void update() {
		double v =  (double)(sprite.getGlobalTime())/(sprite.getMaxTime());
		view.setProgress(v);
		view.update(sprite.getSourceBox(), sprite.getDestBox());
	}
	
	public Node getView() {
		return view;
	}
	public VBox getLayout() {
		return view;
	}
	
	public void checkAnimePause() {
		if ( sprite.isPaused() ) {
			paused = true;
			view.getPauseButton().setText("  ▶️\nplay");
		}
		else {
			paused = false;
			view.getPauseButton().setText("   ⏸️\npause");
		}
	}
}
