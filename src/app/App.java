package app;

import javafx.application.Application;
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
		
		Main mainController = new Main();
		Scene scene = new Scene( mainController.getView(), 640, 480 );
        stage.setScene(scene);
        stage.show();
	}
	
	// sera remplacer dans les version ultérieur par
	// Java i18n mais pour simplifier on reste avec ça
	private static Lang lang;
	
	public static Lang getLang() {
		return lang;
	}
	public static void setLang(Lang newLang) {
		lang = newLang;
	}
	// ----------------------------------------
	
    public static void main(String[] args) {
    	
    	System.out.println("Launched");
        launch();
    }
}