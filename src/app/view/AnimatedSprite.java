package app.view;

import java.util.HashMap;

import javafx.animation.AnimationTimer;
import javafx.scene.shape.Rectangle;

import app.Componable;

public class AnimatedSprite extends Sprite implements Componable {

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
				System.out.println("AnimatedSprite");
				nextFrame();
			}
        }
    };
    
	//############################ METHODES #####################################

	/**
	 * constructeur
	 * @param spritesheet l'image des sprites
	 * @param src_x l'origine x du sprite dans l'image
	 * @param src_y l'origine y du sprite dans l'image
	 * @param src_h sa hauteur
	 * @param src_w sa largeur
	 */
	public AnimatedSprite(String spritesheet, int src_x, int src_y, int src_h, int src_w) {
		super(spritesheet, src_x, src_y, src_h, src_w);
		anime_map = new HashMap<>();
		current_frame = 0;
	}
	
	/**
	 * ajoute une animation
	 * @param name l'identifiant de l'animation
	 * @param rects la liste des frames
	 */
	public void addAnime(String name, Rectangle ... rects) {
		anime_map.put(name, rects);
	}
	
	/**
	 * définit l'animation à jouer
	 * @param name l'identifiant de l'animation
	 */
	public void setAnime(String name) {
		current_anime = name;
		current_limit = anime_map.get(current_anime).length;
	}
	
	public void changeLine(int num) {
		System.out.println("changeLine: " + anime_map.keySet());
		
		for (String keys : anime_map.keySet()) {
			System.out.println(keys);
			for (Rectangle frame : anime_map.get(keys)) {
				
				frame.setY(num*512);
				System.out.println(frame);
			}
		}
	}
	
	/**
	 * passe a la frame suivante ou boucle sur la 1er
	 */
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
	
	/**
	 * joue l'animation
	 */
	public void play() {
		animation.start();
	}
	
	/**
	 * stop l'animation
	 */
	public void stop() {
		animation.stop();
	}
	
	@Override
	public void exit() {
		
		cancel();
		
		if ( animation != null ) {
			animation.stop();
			animation = null;
		}
		
		if ( anime_map != null ) {
			anime_map.clear();
			anime_map = null;
		}
	}
}