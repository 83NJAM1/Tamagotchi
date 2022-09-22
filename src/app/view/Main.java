package app.view;

import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Main extends StackPane {

	HBox horizontalPane;
	VBox characteristicPane;
	Canvas drawzone;
	Label name;
	Label bladder;
	Label energy;
	Label hydrated;
	Label hygiene;
	Label nourished;
	Label spirit;
	Label weight;
	
	public Main(double w, double h) {
		super();
		
		horizontalPane = new HBox(32);
		characteristicPane = new VBox(32);
		drawzone = new Canvas(w,h);
		name = new Label();
		bladder = new Label();
		energy = new Label();
		hydrated = new Label();
		hygiene = new Label();
		nourished = new Label();
		spirit = new Label();
		weight = new Label();
		
		horizontalPane.getChildren().add(drawzone);
		characteristicPane.getChildren().add(name);
		characteristicPane.getChildren().add(bladder);
		characteristicPane.getChildren().add(energy);
		characteristicPane.getChildren().add(hydrated);
		characteristicPane.getChildren().add(hygiene);
		characteristicPane.getChildren().add(nourished);
		characteristicPane.getChildren().add(spirit);
		characteristicPane.getChildren().add(weight);
		horizontalPane.getChildren().add(characteristicPane);
		this.getChildren().add(horizontalPane);
	}
	
	public void drawImage(Image img, double x, double y) {
		drawzone.getGraphicsContext2D().drawImage(img, x, y);
	}
	public void blanck() {
		drawzone.getGraphicsContext2D().clearRect(0, 0, drawzone.getWidth(), drawzone.getHeight());
	}
	
	public void changeName(String newName) {
		name.setText("Name: " + newName);
	}
	public void changeBladder(double newValue) {
		bladder.setText( ("Bladder: %.3f").formatted(newValue) );
	}
	public void changeEnergy(double newValue) {
		energy.setText( ("Energy: %.3f").formatted(newValue) );
	}
	public void changeHydrated(double newValue) {
		hydrated.setText( ("Hydrated: %.3f").formatted(newValue) );
	}
	public void changeHygiene(double newValue) {
		hygiene.setText( ("Hygiene: %.3f").formatted(newValue) );
	}
	public void changeNourished(double newValue) {
		nourished.setText( ("Nourished: %.3f").formatted(newValue) );
	}
	public void changeSpirit(double newValue) {
		spirit.setText( ("Spirit: %.3f").formatted(newValue) );
	}
	public void changeWeight(double newValue) {
		weight.setText( ("Weight: %.3f").formatted(newValue) );
	}
}
