package test;

import javafx.application.Application;
import javafx.stage.Stage;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class Test extends Application {
	
	private final double max_w_canvas = 480;
	private final double max_h_canvas = 360;
	
	private MyButton buttonEra;
	private MyButton buttonRed;
	private MyButton buttonBlu;
	private MyButton buttonGre;
	private MyButton buttonBla;
	private MyButton buttonSave;
	private MyCanvas canvas;
	private MyLayout layout;
	private Label message;

	
    public void start(Stage stage) {
    	
        String javaVersion = System.getProperty("java.version");
        String javafxVersion = System.getProperty("javafx.version");
     
        //############### INITIALISATION DES BOUTONS ET AUTRES ###################
        
    	layout = new MyLayout();
    	buttonEra = new MyButton("Gomme");
    	buttonRed = new MyButton("Crayon rouge");
    	buttonBlu = new MyButton("Crayon bleu");
    	buttonGre = new MyButton("Crayon vert");
    	buttonBla = new MyButton("Crayon noir");
    	buttonSave = new MyButton("Save");
    	
    	canvas = new MyCanvas(max_w_canvas, max_h_canvas);
        message = new Label("Hello, JavaFX " + javafxVersion + ", " + 
        					"running on Java " + javaVersion + ".");

        //########################## GESTION DES EVENTS ##########################
        
        //Creating mouse event
        EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                System.out.println("in canvas");            }
        };
        canvas.addEventFilter(MouseEvent.MOUSE_ENTERED_TARGET, eventHandler);
        
        //Creating button action event
        EventHandler<ActionEvent> saveHandler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent action) {
                System.out.println("save clicked");
                canvas.save("snapshot.png");
            }
        };
        buttonSave.setOnAction(saveHandler);
        
        //Creating button action event
        EventHandler<ActionEvent> eraserHandler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent action) {
                System.out.println("eraser clicked");
                canvas.setPen(null);
            }
        };
        buttonEra.setOnAction(eraserHandler);
        
        //Creating button action event
        EventHandler<ActionEvent> redHandler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent action) {
                System.out.println("red clicked");
                canvas.setPen(Color.RED);
            }
        };
        buttonRed.setOnAction(redHandler);
        
        //Creating button action event
        EventHandler<ActionEvent> blueHandler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent action) {
                System.out.println("blue clicked");
                canvas.setPen(Color.BLUE);
            }
        };
        buttonBlu.setOnAction(blueHandler);
        
        //Creating button action event
        EventHandler<ActionEvent> greenHandler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent action) {
                System.out.println("green clicked");
                canvas.setPen(Color.GREEN);
            }
        };
        buttonGre.setOnAction(greenHandler);
        
        //Creating button action event
        EventHandler<ActionEvent> blackHandler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent action) {
                System.out.println("black clicked");
                canvas.setPen(Color.BLACK);
            }
        };
        buttonBla.setOnAction(blackHandler);
        
        
        //Creation change event for width change
        ChangeListener<Number> newWidth = new ChangeListener<Number>() {
        	public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
        		Double width = ((Double)newValue)-layout.getRightLeftWidth();
        		/*Double oldWidth = (Double)oldValue;
        		if (oldWidth < max_w_canvas) {
        			canvas.setWidth(width);
        			canvas.draw();
        		}
        		else {
        			canvas.setWidth(max_w_canvas);
        			canvas.draw();
        		}*/
        		canvas.setWidth(width);
        		canvas.draw();
        		
        	}
        };
        layout.widthProperty().addListener(newWidth);
        
        //Creation change event for height change
        ChangeListener<Number> newHeight= new ChangeListener<Number>() {
        	public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
        		Double height = ((Double)newValue)-layout.getTopBottomHeight();
        		/*Double oldHeight = (Double)oldValue;
        		if (oldHeight < max_h_canvas) {
        			canvas.setHeight(height);
        			canvas.draw();
        		}
        		else {
        			canvas.setHeight(max_h_canvas);
        			canvas.draw();
        		}*/
        		canvas.setHeight(height);
        		canvas.draw();
        	}
        };
        layout.heightProperty().addListener(newHeight);
        
        //###################### CONSTRUCTION DE LA FENETRE ######################
        
        Scene scene = new Scene(layout, 640, 480);
        
        layout.addTop(buttonEra);
        layout.addTop(buttonRed);
        layout.addTop(buttonBlu);
        layout.addTop(buttonGre);
        layout.addTop(buttonBla);
        layout.addTop(buttonSave);
        layout.addBottom(message);
        layout.addCenter(canvas);
        
        // utile pour appeler ChangeListener a l'initialisation
        stage.setWidth(640);
        stage.setHeight(480);
        
        stage.setScene(scene);
        stage.show();
    }
    
    public static void main(String[] args) {
    	System.out.println("Launched");
        launch();
    }
	
}
