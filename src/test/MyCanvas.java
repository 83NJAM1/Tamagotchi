package test;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

import javax.imageio.ImageIO;
import java.io.File;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.ArcType;

public class MyCanvas extends Canvas{

	private GraphicsContext gc;
	private double default_width;
	private double default_height;
	private Color colorPen;
	
	EventHandler<MouseEvent> eraser = new EventHandler<MouseEvent>() {
         @Override
         public void handle(MouseEvent e) {
        	 if (colorPen != null) {
        		 gc.setFill(colorPen);
        		 gc.fillOval(e.getX() - 5, e.getY() - 5, 10, 10);
        	 }
        	 else {
        		 gc.clearRect(e.getX() - 8, e.getY() - 8, 16, 16);
        	 }
         }
     };
	
	public MyCanvas(double width, double height) {
		super(width, height);
		colorPen = null;
		default_width = width;
		default_height = height;
		
		gc = getGraphicsContext2D();
	    draw();
	    
	    this.addEventHandler(MouseEvent.MOUSE_DRAGGED, eraser);
	}
	
	public void draw() {
		gc.setFill(Color.YELLOWGREEN);
		gc.fillRect(0, 0, getWidth(), getHeight());
		gc.setLineWidth(4);
		gc.strokeRect(2, 2, getWidth()-4, getHeight()-4);
		gc.setFill(Color.YELLOW);
		gc.fillArc(0, 0, getWidth(), getHeight(), 45, 260, ArcType.ROUND );
		gc.strokeArc(0, 0, getWidth(), getHeight(), 45, 260, ArcType.ROUND );
		gc.setFill(Color.BLACK);
		gc.fillOval(getWidth()*0.37, getHeight()*0.17, getWidth()*0.1, getHeight()*0.1);
	}
	
	public void resetSize() {
		this.setWidth(default_width);
		this.setHeight(default_height);
		draw();
	}
	
	public void setPen(Color color) {
		colorPen = color;
	}
	
	public void save(String path) {
		WritableImage export = new WritableImage((int)getWidth(), (int)getHeight());
		File file = new File(path);
		try {
			this.snapshot(null, export);
			ImageIO.write(SwingFXUtils.fromFXImage(export, null), "png", file);
		}
		catch(Exception e) {
			System.err.println("Error save image");
		}
	}
}
