package app.controller;

import java.nio.file.Paths;
import java.text.NumberFormat;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

import app.App;
import javafx.animation.AnimationTimer;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

/**
 * 
 * @author ben
 * Controller principale encapsulant tout
 * il fait le lien avec le thread javafx
 */
public class Main {
	
	//########################### ATTRIBUTS #####################################
	
	private app.view.Main view;
	private app.model.Save save;
	
	private Menu menu;
	private Game game;
	private Media music;
	private MediaPlayer mediaplayer;
	
	private BooleanProperty gameover;
	//######################### EVENT-ACTION ####################################
	
	/**
	 * ActionEvent effectué quand t-on modifie le volume via la vue Option
	 * déclencher -> this -> v.Menu -< v.Option
	 */
	ChangeListener<Boolean> change_gameover_value = new ChangeListener<Boolean>() {
		public void changed(ObservableValue<? extends Boolean> obs, Boolean vold, Boolean vnew) {
			mediaplayer.stop();
			System.out.println(Paths.get("bin/res/musiques/LowBattery.mp3").toUri().toString());
			music = new Media(Paths.get("bin/res/musiques/LowBattery.mp3").toUri().toString());
			mediaplayer = new MediaPlayer(music);
			mediaplayer.play();
			mediaplayer.setVolume(menu.getVolume());
		}
	};
	
	/**
	 * ActionEvent effectué quand t-on modifie le volume via la vue Option
	 * déclencher -> this -> v.Menu -< v.Option
	 */
	ChangeListener<Number> change_volume_value = new ChangeListener<Number>() {
		public void changed(ObservableValue<? extends Number> obs, Number vold, Number vnew) {
			System.out.println(vold + " to " + vnew);
			menu.setVolume(vnew.doubleValue());
			mediaplayer.setVolume(vnew.doubleValue());
		}
	};
	
	/**
	 * ActionEvent effectué quand t-on change la langue via la vue Option
	 * déclencher -> this -> v.Menu -< v.Option
	 */
	private EventHandler<ActionEvent> choose_lang = new EventHandler<ActionEvent>() {
		public void handle(ActionEvent e) {
			Locale loc;
			if ( menu.getView().getOption().getChoosenLang() == 0) {
				loc = Locale.FRENCH;
				App.language = ResourceBundle.getBundle("language", loc);
				App.languageNumber = NumberFormat.getNumberInstance(loc);
			}
			else {
				loc = Locale.ENGLISH;
				App.language = ResourceBundle.getBundle("language", loc);
				App.languageNumber = NumberFormat.getNumberInstance(loc);
			}
			
			System.out.println("Locale:" + loc.getDisplayLanguage());

			menu.getView().updateText();
			game.getView().updateText();
			game.getPet().updateText();
		}
	};
	
	//############################ METHODES #####################################

	public Main() {
		save = new app.model.Save("./res/save.tmg");
		menu = new Menu();
		game = new Game();
		view = new app.view.Main( game.getView(), menu.getView() );
		System.out.println(Paths.get("bin/res/musiques/DogsAndCats.mp3").toUri().toString());
		music = new Media(Paths.get("bin/res/musiques/DogsAndCats.mp3").toUri().toString());
		mediaplayer = new MediaPlayer(music);
		mediaplayer.setStopTime(Duration.seconds(113));
		mediaplayer.setCycleCount(MediaPlayer.INDEFINITE);
		mediaplayer.play();
		mediaplayer.setVolume(0.5);
		gameover = new SimpleBooleanProperty(this, "gameover", false);
		
		gameover.addListener(change_gameover_value);
		gameover.bind(game.gameover);
		
		menu.getView().getOption().setLangAction(choose_lang);
		menu.getView().getOption().setVolumeAction(change_volume_value);
	}
	
	public app.view.Main getView() {
		return view;
	}
	
	public Menu getMenu() {
		return menu;
	}
	
	public Game getGame() {
		return game;
	}
	
	/**
	 * essaie d'aider au mieu le Garbage Collector
	 */
	public void exit() {
		mediaplayer.stop();
		save.saveToDisk();
		game.exit();
		menu.exit();
		view = null;
		save = null;
		menu = null;
		game = null;
		choose_lang = null;
		mediaplayer=null;
		music=null;
	}
}
