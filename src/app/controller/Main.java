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
	private Media musicBackground;
	private Media musicGameover;
	private MediaPlayer mediaplayer;
	
	private BooleanProperty gameover;
	
	//######################### EVENT-ACTION ####################################
	
	/**
	 * Action effectué quand t-on perd la partie
	 * déclencher -> this -> v.Menu -< v.Option
	 */
	ChangeListener<Boolean> change_gameover_value = new ChangeListener<Boolean>() {
		public void changed(ObservableValue<? extends Boolean> obs, Boolean vold, Boolean vnew) {
			setGameoverMusic();
		}
	};
	
	/**
	 * Action effectué quand t-on modifie le volume via la vue Option
	 * déclencher -> this -> v.Menu -< v.Option
	 */
	ChangeListener<Number> change_volume_value = new ChangeListener<Number>() {
		public void changed(ObservableValue<? extends Number> obs, Number vold, Number vnew) {
			menu.setVolume(vnew.doubleValue());
			mediaplayer.setVolume(vnew.doubleValue());
			
			menu.getOption().save();
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
			}
			else {
				loc = Locale.ENGLISH;
			}
			
			App.language = ResourceBundle.getBundle("language", loc);
			App.languageNumber = NumberFormat.getNumberInstance(loc);
			
			updateText();
			menu.getOption().setLanguage(loc.toString());
			menu.getOption().save();
			
			System.out.println("Locale:" + loc.getDisplayLanguage());
		}
	};
	
	//############################ METHODES #####################################

	public Main(String pathsave) {
		
		menu = new Menu();
		game = new Game();
		
		if ( pathsave != null ) {
			System.out.println("current save : " + pathsave);
			save = new app.model.Save("res/save2.tmg", game.getModel());
		}
		else
			save = new app.model.Save("res/save.tmg", game.getModel());
		
		view = new app.view.Main( game.getView(), menu.getView() );
		gameover = new SimpleBooleanProperty(this, "gameover", false);
		musicBackground = new Media(Paths.get("bin/res/musiques/DogsAndCats.mp3").toUri().toString());
		musicGameover = new Media(Paths.get("bin/res/musiques/LowBattery.mp3").toUri().toString());

		if ( pathsave != null )
			loadGame(pathsave);
		
		menu.loadOption();
		changeGameDim(menu.getChoosenDim());
		updateText();
		setBackgroundMusic();
		
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
	
	public void updateText() {
		menu.getView().updateText();
		game.getView().updateText();
		game.getPet().updateText();
	}
	
	public void changeGameDim(int numChoice) {
		game.getView().changeDimension(numChoice);
	}
	
	public void setBackgroundMusic() {
		mediaplayer = new MediaPlayer(musicBackground);
		mediaplayer.setStopTime(Duration.seconds(113));
		mediaplayer.setCycleCount(MediaPlayer.INDEFINITE);
		mediaplayer.play();
		mediaplayer.setVolume(menu.getVolume());
		System.out.println("Play: " + musicBackground.getSource());
	}
	
	public void setGameoverMusic() {
		mediaplayer.stop();
		mediaplayer = new MediaPlayer(musicGameover);
		mediaplayer.play();
		mediaplayer.setVolume(menu.getVolume());
		System.out.println("Play: " + musicGameover.getSource());
	}
	
	// Test save
	public void loadGame(String pathsave) {
		System.out.println("Loading... : " + pathsave);
		System.out.println(save.load("./res/save2.tmg"));
	}
	
	// Test save
	public void saveGame() {
		save.save();
		System.out.println("Save done");
	}
	
	/**
	 * essaie d'aider au mieu le Garbage Collector
	 */
	public void exit() {
		mediaplayer.stop();
		game.exit();
		menu.exit();
		view = null;
		save = null;
		menu = null;
		game = null;
		choose_lang = null;
		mediaplayer=null;
		musicBackground=null;
		musicGameover=null;
	}
}
