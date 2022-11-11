package app;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

import app.controller.Main;
import app.lang.Lang;
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
	
	private static Lang lang;
	
	public static Lang getLang() {
		return lang;
	}
	public static void setLang(Lang newLang) {
		lang = newLang;
	}
	
    public static void main(String[] args) {
    	
    	System.out.println("Launched");
        launch();
    }
}