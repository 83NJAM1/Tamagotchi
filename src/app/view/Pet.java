package app.view;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.image.Image;

public class Pet {

	private Image skin;
	private IntegerProperty x;
	private IntegerProperty y;
	
	public Pet(String pathimage) {
		
		x = new SimpleIntegerProperty(98);
		y = new SimpleIntegerProperty(98);
		skin = new Image(pathimage);
	}
	
	public Image getImage() {
		
		return skin;
	}
	
	public IntegerProperty getWritableX() {
		
		return x;
	}
	
	public IntegerProperty getWritableY() {
		
		return y;
	}
}
