package test.old.View;

import java.util.HashMap;

import app.App;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.BoundingBox;
import javafx.scene.image.Image;

public class Pet {

	private Image skin;
	private Image sprites;
	private IntegerProperty x;
	private IntegerProperty y;
	private HashMap<String, BoundingBox> spritesMap;
	
	public Pet(String pathimage) {
		
		spritesMap = new HashMap<String, BoundingBox>();
		x = new SimpleIntegerProperty(98);
		y = new SimpleIntegerProperty(98);
		skin = new Image(pathimage);
		sprites = new Image(App.getResource("res/texture_test.png"));
		spritesMap.put("A", new BoundingBox(0, 0, 64, 64));
		spritesMap.put("B", new BoundingBox(64, 0, 64, 64));
		spritesMap.put("C", new BoundingBox(128, 0, 64, 64));
		spritesMap.put("D", new BoundingBox(192, 0, 64, 64));
		spritesMap.put("O", new BoundingBox(192, 192, 64, 64));
		
	}
	
	public Image getImage() {
		
		return skin;
	}
	public Image getSpritesImage() {
		
		return sprites;
	}
	public BoundingBox getSprite(String name) {
		return spritesMap.get(name);
	}
	
	public IntegerProperty getWritableX() {
		
		return x;
	}
	
	public IntegerProperty getWritableY() {
		
		return y;
	}
}
