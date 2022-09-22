package app.view;

import javafx.scene.image.Image;

public class Pet {

	Image skin;
	
	public Pet(String pathimage) {
		skin = new Image(pathimage);
	}
	
	public Image getImage() {
		return skin;
	}
}
