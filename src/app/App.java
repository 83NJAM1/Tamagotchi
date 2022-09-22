package app;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import app.controller.Main;

public class App extends Application{
	
	private Main mainController;
	
	public void start(Stage stage) {
		mainController = new Main();

		Scene scene = new Scene(mainController.getRoot(), 640, 480);
        stage.setScene(scene);
        stage.show();
	}
	public static String getRessource(String path) {
		return App.class.getClassLoader().getResource(path).toString();
	}
    public static void main(String[] args) {
    	System.out.println("Launched");
        launch();
    }
}
