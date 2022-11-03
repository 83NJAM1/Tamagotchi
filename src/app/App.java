package app;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import app.controller.*;

public class App extends Application{
	
	public void start(Stage stage) {
		
		//Main mainController = new Main();
		Controler.start();
		Scene scene = new Scene(Controler.getRoot(), 640, 480);
        stage.setScene(scene);
        stage.show();
	}
	
	public static String getResource(String path) {

		try {
			
			return App.class.getClassLoader().getResource(path).toString();
		}
		catch(NullPointerException e) {
			
			System.out.println("Error resources loading");
			System.out.println(e);
			System.exit(-1);
			return "";
		}
	}
	
    public static void main(String[] args) {
    	
    	System.out.println("Launched");
        launch();
    }
}
