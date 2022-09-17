package test;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class MyCanvas extends Canvas{

	private GraphicsContext gc;
	
	public MyCanvas(double width, double height) {
		super(width, height);
		
		gc = getGraphicsContext2D();
		
		gc.clearRect(0, 0, width, height);
		gc.setStroke(Color.RED);
		gc.strokeLine(0, 0, width, height);
		gc.strokeLine(0, height, width, 0);
	}
}
