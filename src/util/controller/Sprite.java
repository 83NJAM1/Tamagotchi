package util.controller;

import java.util.HashMap;

import javafx.geometry.BoundingBox;
import javafx.scene.image.Image;

public class Sprite {
	//private util.view.Sprite view;
	private util.model.Sprite model;
	private util.view.Sprite view;
	
	// ###################################################### 
	private HashMap<String, Anime> animations; // ########### TODO À REMPLACER PAR ANIME LISTE !!
	// ######################################################
	
	public Sprite() {
		model = new util.model.Sprite();
		view = new util.view.Sprite();
		animations = new HashMap<String, Anime>();
	}
	
	public void addAnime(Anime new_anime) {
		if ( animations.get(new_anime.getNameId()) == null ) {
			animations.put(new_anime.getNameId(), new_anime);
		}
		model.addAnime(new_anime.getModel());
	}
	public void removeAnime(String id) {
		if ( animations.get(id) != null ) {
			animations.remove(id);
		}
		model.removeAnime(id);
	}
	public void setAnime(String id) {
		model.setAnime(id);
	}
	public String getAnime() {
		return model.getAnime();
	}
	public String getSpriteSheet() {
		return model.getSpriteSheet();
	}
	public long getGlobalTime() {
		return model.getGlobalTime();
	}
	public long getCurrentTime() {
		return model.getCurrentTime();
	}
	public long getMaxTime() {
		return model.getMaxTime();
	}
	public void setSpriteSheet(String path) {
		view.setSpriteSheet(path);
	}
	public Image getImage() {
		return view.getImage();
	}
	public BoundingBox getSourceBox() {
		return model.getSourceBox();
	}
	public BoundingBox getDestBox() {
		return model.getDestBox();
	}
	public void play() {
		model.play();
	}
	public void pause() {
		model.pause();
	}
	public boolean isPaused() {
		return model.isPaused();
	}
	public Frame run(long time) {
		model.run(time);
		return null;
	}
	public String getCurrentFrame() {
		return model.getAnime() + ":" + model.getCurrentFrame();
	}
}
