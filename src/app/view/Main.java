package app.view;

import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class Main extends StackPane {
	
	private HBox horizontalPane;
	private VBox characteristicPane;
	private Canvas drawzone;
	private Label name;
	private Label bladder;
	private Label energy;
	private Label hydrated;
	private Label hygiene;
	private Label nourished;
	private Label spirit;
	private Label weight;
	private Font nameFont;
	
	public Main(double width, double height) {
		super();
		
		nameFont = Font.font("Courier new", FontWeight.BOLD, FontPosture.ITALIC, 24);
		horizontalPane = new HBox(32);
		characteristicPane = new VBox(32);
		drawzone = new Canvas(width, height);
		name = new Label();
		bladder = new Label();
		energy = new Label();
		hydrated = new Label();
		hygiene = new Label();
		nourished = new Label();
		spirit = new Label();
		weight = new Label();
		
		this.setBackground(new Background( new BackgroundFill(Color.BEIGE, null, null) ));
		this.getStylesheets().add("./res/myStyle.css");
		characteristicPane.getStyleClass().add("char-pane");
		name.setFont(nameFont);
		name.setUnderline(true);
		
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
		
		changeTextColorByValue(newValue, bladder);
		bladder.setText( ("Bladder: %.3f").formatted(newValue) );
	}
	
	public void changeEnergy(double newValue) {
		
		changeTextColorByValue(newValue, energy);
		energy.setText( ("Energy: %.3f").formatted(newValue) );
	}
	
	public void changeHydrated(double newValue) {
		
		changeTextColorByValue(newValue, hydrated);
		hydrated.setText( ("Hydrated: %.3f").formatted(newValue) );
	}
	
	public void changeHygiene(double newValue) {
		
		changeTextColorByValue(newValue, hygiene);
		hygiene.setText( ("Hygiene: %.3f").formatted(newValue) );
	}
	
	public void changeNourished(double newValue) {
		
		changeTextColorByValue(newValue, nourished);
		nourished.setText( ("Nourished: %.3f").formatted(newValue) );
	}
	
	public void changeSpirit(double newValue) {
		
		changeTextColorByValue(newValue, spirit);
		spirit.setText( ("Spirit: %.3f").formatted(newValue) );
	}
	
	public void changeWeight(double newValue) {
		
		changeTextColorByValue(newValue, weight);
		weight.setText( ("Weight: %.3f").formatted(newValue) );
	}
	
	private void changeTextColorByValue(double value, Label l) {
		
		if (value > 0.5) {
			l.setTextFill(Color.GREEN);
		}
		else if (value > 0.2) {
			l.setTextFill(Color.ORANGE);
		}
		else {
			l.setTextFill(Color.RED);
		}
	}
}
