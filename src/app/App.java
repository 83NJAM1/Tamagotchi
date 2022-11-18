package app;

//javafx import
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
	
	private Main mainController;
	private Stage stage;
	
	private EventHandler<ActionEvent> choose_dim = new EventHandler<ActionEvent>() {
		public void handle(ActionEvent e) {
			if ( mainController.getMenu().getView().getOption().getChoosenDim() == 0) {
				stage.setWidth(640);
				stage.setHeight(360);
			}
			else {
				stage.setWidth(1280);
				stage.setHeight(720);
			}
		}
	};
	
	public void start(Stage stage) {
		this.stage = stage;
		mainController = new Main();
		mainController.getMenu().getView().getOption().setDimensionAction(choose_dim);
		Scene scene = new Scene( mainController.getView(), 640, 360);
        stage.setScene(scene);
        stage.show();
	}
	
	//fichiers .properties requis accesible dans le ./res à mettre dans le dossier ./bin
	//soit ./bin/language.properties et ./bin/language_en.properties
	public static ResourceBundle language = ResourceBundle.getBundle("language", Locale.ENGLISH);
	
    public static void main(String[] args) {
    	
    	System.out.println(language.getString("wellcome"));
        launch();
    }
}