package util;

import util.controller.*;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Window;

public class SpritesViewer extends Application{
	
	public void start(Stage stage) {
		
		Main mainController = new Main();
		Scene scene = new Scene(mainController.getView(), 640, 480);
		
		ChangeListener<Number> winchange = new ChangeListener<Number>() {
			public void changed(ObservableValue<? extends Number> obs, Number old_v, Number new_v) {
				if (old_v != null) {
					double nw = new_v.doubleValue();
					//double nh = new_v.doubleValue();
					double ow = old_v.doubleValue();
					//double oh = old_v.doubleValue();
					if ( nw != ow ) {
						mainController.update();
					}
					System.out.println(""+nw);
				}
				System.out.println("called");
			}
		};
		
		//scene.windowProperty().addListener(winchange);
		scene.widthProperty().addListener(winchange);
		scene.heightProperty().addListener(winchange);
        stage.setScene(scene);
        stage.show();
	}
	
	public static String getResource(String path) {
		
		try {
			
			return SpritesViewer.class.getClassLoader().getResource(path).toString();
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