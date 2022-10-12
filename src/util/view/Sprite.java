package util.view;

import javafx.scene.image.Image;
import util.SpritesViewer;

public class Sprite {
	Image image;
	
	public void setSpriteSheet(String path) {
		image = new Image(SpritesViewer.getResource(path));
	}
	public Image getImage() {
		return image;
	}
}
