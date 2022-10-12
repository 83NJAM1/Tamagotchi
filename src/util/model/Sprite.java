package util.model;

import java.util.HashMap;

import javafx.geometry.BoundingBox;

public class Sprite {
	private HashMap<String, Anime> animations;
	private String currentAnime;
	
	public Sprite() {
		animations = new HashMap<String, Anime>();
	}
	
	public void addAnime(Anime new_anime) {
		if ( animations.get(new_anime.getId()) == null ) {
			animations.put(new_anime.getId(), new_anime);
			
			System.out.println(new_anime.getId()+" added to " + this);
		}
	}
	
	public void removeAnime(String id) {
		if ( animations.get(id) != null ) {
			animations.remove(id);
		}
	}
	
	public void setAnime(String id) {
		currentAnime = id;
	}
	
	public String getAnime() {
		return currentAnime;
	}
	
	public String getSpriteSheet() {
		return animations.get(currentAnime).getSpriteSheet();
	}
	
	public long getGlobalTime() {
		return animations.get(currentAnime).getGlobalTime();
	}
	
	public long getMaxTime() {
		return animations.get(currentAnime).getMaxTime();
	}
	
	public long getCurrentTime() {
		return animations.get(currentAnime).getCurrentTime();
	}
	
	public BoundingBox getSourceBox() {
		try {
			return animations.get(currentAnime).getSourceBox();
		}
		catch(NullPointerException e) {
			System.out.println(e);
			return null;
		}
	}
	
	public BoundingBox getDestBox() {
		try {
			return animations.get(currentAnime).getDestBox();
		}
		catch(NullPointerException e) {
			System.out.println(e);
			return null;
		}
	}
	
	public void play() {
		animations.get(currentAnime).play();
	}
	
	public void pause() {
		animations.get(currentAnime).pause();
	}
	
	public boolean isPaused() {
		return animations.get(currentAnime).isPaused();
	}
	
	public Frame run(long time) {
		try {
			return animations.get(currentAnime).run();
		}
		catch(NullPointerException e) {
			System.out.println(e);
			return null;
		}
	}
	
	public int getCurrentFrame() {
		return animations.get(currentAnime).getCurrentFrame();
	}
}
