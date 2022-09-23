package test;

import javafx.application.Application;
import javafx.stage.Stage;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

public class Test extends Application {
	
	private final double max_w_canvas = 480;
	private final double max_h_canvas = 360;
	
	private EraserButton buttonEra;
	private PenColorButton buttonRed;
	private PenColorButton buttonBlu;
	private PenColorButton buttonGre;
	private PenColorButton buttonBla;
	private SaveButton buttonSave;
	private MyCanvas canvas;
	private MyLayout layout;
	private Label message;

	
    public void start(Stage stage) {
    	
    	stage.setTitle("My TEST JavaFX Application");
        String javaVersion = System.getProperty("java.version");
        String javafxVersion = System.getProperty("javafx.version");
     
        //############### INITIALISATION OF NODES ###################
        
    	layout = new MyLayout();
    	buttonEra = new EraserButton("Gomme");
    	buttonRed = new PenColorButton("Crayon rouge", Color.RED);
    	buttonBlu = new PenColorButton("Crayon bleu", Color.BLUE);
    	buttonGre = new PenColorButton("Crayon vert", Color.GREEN);
    	buttonBla = new PenColorButton("Crayon noir", Color.BLACK);
    	buttonSave = new SaveButton("Save", "snapshot.png");
    	
    	canvas = new MyCanvas(max_w_canvas, max_h_canvas);
        message = new Label("Hello, JavaFX " + javafxVersion + ", " + 
        					"running on Java " + javaVersion + ".");

        //########################## EVENTS HANDLING ##########################
        
        //Associates buttons action with canvas
        buttonSave.setActionFor(canvas);
        buttonEra.setActionFor(canvas);
        buttonRed.setActionFor(canvas);
        buttonBlu.setActionFor(canvas);
        buttonGre.setActionFor(canvas);
        buttonBla.setActionFor(canvas);
        
        //Associates canvas size with layout size
        canvas.widthProperty().bind(layout.getCenter().widthProperty().subtract(8.));
        canvas.heightProperty().bind(layout.getCenter().heightProperty().subtract(8.));

        //###################### BUILDS THE WINDOW ######################
        
        Scene scene = new Scene(layout, 640, 480);
        
        layout.addTop(buttonEra);
        layout.addTop(buttonRed);
        layout.addTop(buttonBlu);
        layout.addTop(buttonGre);
        layout.addTop(buttonBla);
        layout.addTop(buttonSave);
        layout.addBottom(message);
        layout.addCenter(canvas);

        stage.setScene(scene);
        stage.show();
        
        //print a basic draw at launch
        //to redraw the canvas on resize uncomment withProperty/heightProperty.addListener in the MyCanvas constructor
        canvas.draw();
    }
    
    public static void main(String[] args) {
    	System.out.println("Launched");
        launch();
    }
	
}
