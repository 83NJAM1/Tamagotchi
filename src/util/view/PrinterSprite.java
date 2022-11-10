package util.view;

import util.SpritesViewer;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.BoundingBox;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class PrinterSprite extends VBox {
	
	private Canvas printer;
	private Button pauseButton;
	private ProgressBar timelaps;
	private String spriteSheet;
	private Image sprite;
	
	public PrinterSprite(double w, double h, EventHandler<ActionEvent> pauseAction) {
		this.setStyle("-fx-background-color: yellow;");
		printer = new Canvas(w,h);
		printer.getGraphicsContext2D().setFill(Color.RED);
		printer.getGraphicsContext2D().fillRect(0, 0, w, h);
		printer.getGraphicsContext2D().setImageSmoothing(false);
		pauseButton = new Button("  ▶️\nplay");
		timelaps = new ProgressBar();

		pauseButton.setOnAction(pauseAction);
	    spriteSheet="";
	    sprite = null;
	    timelaps.setPrefWidth(w);
	    pauseButton.setPrefWidth(w);
	    
	    this.setProgress(0.5);
	    this.getChildren().add(printer);
		this.getChildren().add(timelaps);
		this.getChildren().add(pauseButton);
	}
	public void update(BoundingBox src, BoundingBox dest) {
		printer.setWidth((sceneProperty().get().getWidth()-10)*0.65);
		printer.setHeight((sceneProperty().get().getHeight()-10)*0.50);
		timelaps.setPrefWidth(printer.getWidth());
		draw(src,dest);
	}
	public Button getPauseButton() {
		return pauseButton;
	}
	public void setSpriteSheet(String spriteSheet) {
		this.spriteSheet = spriteSheet;
		sprite = new Image(SpritesViewer.getResource(this.spriteSheet));
	}
	public void setProgress(double value) {
		timelaps.setProgress(value);
	}
	public void draw(Image img, BoundingBox src, BoundingBox dest, boolean center) {
		if (src != null && dest != null) {
			if (center) {
				printer.getGraphicsContext2D().drawImage(img, src.getMinX(), src.getMinY(), 
															  src.getWidth(), src.getHeight(), 
															  ((printer.getWidth()-dest.getWidth())/2)+dest.getMinX(), ((printer.getHeight()-dest.getHeight())/2)+dest.getMinY(), 
															  dest.getWidth(), dest.getHeight());
			}
			else {
				printer.getGraphicsContext2D().drawImage(img, src.getMinX(), src.getMinY(), 
															  src.getWidth(),  src.getHeight(), 
															  dest.getMinX(), dest.getMinY(), 
															  dest.getWidth(), dest.getHeight());
			}
		}
		//System.out.println("drawing: " + img);
	}
	public void draw(BoundingBox src, BoundingBox dest) {
		draw(sprite, src, dest, true);
	}
	public void blank() {
		printer.getGraphicsContext2D().setFill(Color.LIGHTCYAN);
		printer.getGraphicsContext2D().fillRect(0, 0, printer.getWidth(), printer.getHeight());
	}
}
