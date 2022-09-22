package app.controller;

import javafx.scene.image.Image;

public class Pet {

	private app.model.Pet model;
	private app.view.Pet view;
	
	public Pet(String name, String pathimage) {
		
		model = new app.model.Pet(name);
		view = new app.view.Pet(pathimage);
	}
	
	public String getName() {
		return model.getName();
	}
	public void autoDecrement() {
		model.autoDecrement();
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
