package app.controller;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.util.Duration;

public class Pet {

	private app.model.Pet model;
	private app.view.Pet view;
	
	final Timeline timeline;
	
	public Pet(String name, String pathimage) {
		
		model = new app.model.Pet(name);
		view = new app.view.Pet(pathimage);
		
		timeline = new Timeline();
		timeline.setCycleCount(-1);
		timeline.setAutoReverse(true);
		timeline.getKeyFrames().add(new KeyFrame(Duration.millis(2000), new KeyValue(view.getWritableX(), 62)));
		timeline.play();
	}
	
	public String getName() {
		return model.getName();
	}
	public void autoDecrement() {
		model.autoDecrement();
	}
	
	public int getX() {
		return view.getWritableX().intValue();
	}
	public int getY() {
		return view.getWritableY().intValue();
	}
	
	public double getBladder() {
		return model.getCharacteristic()[0].getValue();
	}
	
	public double getEnergy() {
		return model.getCharacteristic()[1].getValue();
	}
	
	public double getHydrated() {
		return model.getCharacteristic()[2].getValue();
	}
	
	public double getHygiene() {
		return model.getCharacteristic()[3].getValue();
	}
	
	public double getNourished() {
		return model.getCharacteristic()[4].getValue();
	}
	
	public double getSpirit() {
		return model.getCharacteristic()[5].getValue();
	}
	
	public double getWeight() {
		return model.getCharacteristic()[6].getValue();
	}
	
	public Image getSkin() {
		return view.getImage();
	}
}
