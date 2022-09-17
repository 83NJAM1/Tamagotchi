package test;

import javafx.application.Application;
import javafx.stage.Stage;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;

public class Test extends Application {
	
	private MyButton button;
	private MyCanvas canvas;
	private MyLayout layout;
	private Label message;
	
    public void start(Stage stage) {
    	
        String javaVersion = System.getProperty("java.version");
        String javafxVersion = System.getProperty("javafx.version");
        
    	layout = new MyLayout();
    	button = new MyButton();
    	canvas = new MyCanvas(480, 360);
        message = new Label("Hello, JavaFX " + javafxVersion + ", " + 
        					"running on Java " + javaVersion + ".");
        
        //Creating mouse event
        EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                System.out.println("in canvas");            }
        };
        canvas.addEventFilter(MouseEvent.MOUSE_ENTERED_TARGET, eventHandler);
        
        //Creating button action event
        EventHandler<ActionEvent> buttonHandler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent action) {
                System.out.println("button clicked");
            }
        };
        button.setOnAction(buttonHandler);
        
        layout.addTop(button);
        layout.addBottom(message);
        layout.addCenter(canvas);
        Scene scene = new Scene(layout, 640, 480);
        stage.setScene(scene);
        stage.show();
    }
    
    public static void main(String[] args) {
    	System.out.println("Lancement");
        launch();
    }
	
}
