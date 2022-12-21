package app.view;

import java.util.HashMap;

import javafx.animation.AnimationTimer;
import javafx.scene.shape.Rectangle;

public class AnimatedSprite extends Sprite {

	//########################### ATTRIBUTS #####################################

	// map d'une liste de frame
	HashMap<String, Rectangle[]> anime_map;
	
	// l'animation courante
	String current_anime;
	
	// la frame courante de l'animation en cours
	int current_frame;
	
	// le nombre maximum de frame pour l'animation en cours
	int current_limit;
	
	//######################### EVENT-ACTION ####################################

	/**
	 * animationloop effectué toute les 1 second
	 * déclendeur -> this
	 */ 
	private AnimationTimer animation = new AnimationTimer() {
		
		long old_time=0;
		
        public void handle(long new_time) {
			if (new_time > old_time ) {
				old_time = new_time+1_000_000_000;
				
				nextFrame();
			}
        }
    };
    
	//############################ METHODES #####################################

	public AnimatedSprite(String spritesheet, int src_x, int src_y, int src_h, int src_w) {
		super(spritesheet, src_x, src_y, src_h, src_w);
		anime_map = new HashMap<>();
		current_frame = 0;
	}
	
	public void addAnime(String name, Rectangle ... rects) {
		anime_map.put(name, rects);
	}
	
	public void setAnime(String name) {
		current_anime = name;
		current_limit = anime_map.get(current_anime).length;
	}
	
	public void nextFrame() {
		
		if ( current_frame+1 < current_limit ) {
			current_frame++;
		}
		else {
			current_frame = 0;
		}
		
		src_x = anime_map.get(current_anime)[current_frame].getX();
		src_y = anime_map.get(current_anime)[current_frame].getY();
		src_h = anime_map.get(current_anime)[current_frame].getHeight();
		src_w = anime_map.get(current_anime)[current_frame].getWidth();
	}
	
	public void play() {
		animation.start();
	}
	
	public void stop() {
		animation.stop();
	}
	
	public void exit() {
		super.exit();
		animation.stop();
		anime_map.clear();
		anime_map = null;
	}
}
