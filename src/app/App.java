package app;

//javafx import
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

//javasdk import
import java.util.Locale;
import java.util.ResourceBundle;

//app import
import app.controller.Main;

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
	
	static ResourceBundle lang = ResourceBundle.getBundle("language", Locale.ENGLISH);
	
    public static void main(String[] args) {
    	
    	System.out.println(lang.getString("wellcome"));
        launch();
    }
}