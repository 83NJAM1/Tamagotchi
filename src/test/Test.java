package test;

import javafx.application.Application;
import javafx.stage.Stage;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.Group;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;

public class Test extends Application {
	
	MyButton button;
	Circle circle;
	boolean swaper;
	
    public void start(Stage stage) {
    	
        button = new MyButton();
        circle = new Circle();
        swaper = true;
        
        //Setting the position of the circle
        circle.setCenterX(300.0f); 
        circle.setCenterY(135.0f); 
          
        //Setting the radius of the circle
        circle.setRadius(25.0f); 
          
        //Setting the color of the circle 
        circle.setFill(Color.BROWN); 
          
        //Setting the stroke width of the circle 
        circle.setStrokeWidth(20);
        
        //Setting the position of the circle 
        circle.setCenterX(320.0f); 
        circle.setCenterY(135.0f);
    	
        //Creating event
        EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                
                if (swaper) { 
                    circle.setFill(Color.DARKSLATEBLUE); 
                } else {
                    circle.setFill(Color.BROWN);
                }
                System.out.println(""+swaper);
                swaper = !swaper;
            }
        };
        circle.addEventFilter(MouseEvent.MOUSE_ENTERED_TARGET, eventHandler);
        
        //Creating event
        EventHandler<ActionEvent> buttonHandler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent action) {
                System.out.println("clicked");
            }
        };
        
        button.setOnAction(buttonHandler);
        button.addEventFilter(MouseEvent.MOUSE_ENTERED_TARGET, eventHandler);
        
        String javaVersion = System.getProperty("java.version");
        String javafxVersion = System.getProperty("javafx.version");
        Label l = new Label("Hello, JavaFX " + javafxVersion + ", running on Java " + javaVersion + ".");
        l.setLayoutX(150.0f);
        l.setLayoutY(240.0f);
       
        
        Group root = new Group(circle, l, button); 
        Scene scene = new Scene(root, 640, 480);
        stage.setScene(scene);
        
        stage.show();
    }
    
    public static void main(String[] args) {
    	System.out.println("lel");
        launch();
    }
	
}