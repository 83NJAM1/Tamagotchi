package app.view;

import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class View extends Pane {
	
	private Pane boutonsPane;
	private Canvas drawzone;
	
	private Text text;
	
	public View(double width, double height) {
		super();
		
		boutonsPane = new Pane();
		drawzone = new Canvas(width, height);
		
		this.setBackground(new Background( new BackgroundFill(Color.BEIGE, null, null) ));
		//this.getStylesheets().add("./res/myStyle.css");
		
		
		text = new Text();
		text.setTranslateX(450);
		text.setTranslateY(240);

		this.getChildren().addAll(text,boutonsPane);
		
		
		

	}
	
	public void addBouton(int top, int left, String content, String eventCode, int context) {
		
		Bouton button = new Bouton(content,eventCode,context);
		boutonsPane.getChildren().add(button);
		button.setPosition(top,left);
		
		
	}
	
	public void clearBoutons() {
		boutonsPane.getChildren().clear();
	}
	
public void setText(String s) {
		text.setText(s);
	}

public void drawText(Image img, double x, double y) {
			
			drawzone.getGraphicsContext2D().drawImage(img, x, y);
		}
	public void drawImage(Image img, double x, double y) {
		
		drawzone.getGraphicsContext2D().drawImage(img, x, y);
	}
	public void drawSprite(Image img, double x, double y, double w, double h) {
		
		drawzone.getGraphicsContext2D().drawImage(img, x, y, w, h, x, y, w, h);
	}
	
	public void blanck() {
		
		drawzone.getGraphicsContext2D().clearRect(0, 0, drawzone.getWidth(), drawzone.getHeight());
	}
	
	

}
