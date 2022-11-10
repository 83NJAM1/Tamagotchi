package app;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

import app.controller.Main;

/**
 * 
 * @author ben
 * Fenetre de l'application gerer par JavaFX
 */
public class App extends Application{
	
	public void start(Stage stage) {
		
		// Controller
		Main mainController = new Main();
		
		Group vide = new Group();
		Scene scene = new Scene(vide, 640, 480);
        stage.setScene(scene);
        stage.show();
	}
	
    public static void main(String[] args) {
    	
    	System.out.println("Launched");
        launch();
    }
}