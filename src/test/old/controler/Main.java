package test.old.controler;

import app.App;
import javafx.animation.AnimationTimer;
import javafx.scene.layout.StackPane;

public class Main {
	
	private app.view.Main mainView;
	
	private Pet myPet;
	private AnimationTimer timerCharacteristic;
	private AnimationTimer timerRefreshCanvas;

	public Main() {
		
		timerCharacteristic = new AnimationTimer() {
			long old_time=0;
			@Override
	        public void handle(long new_time) {
				if (new_time > old_time ) {
					old_time = new_time+(1000000000);
					updateValue();
				}
	        }
	    };
	    
	    timerRefreshCanvas = new AnimationTimer() {
			long old_time=0;
			@Override
	        public void handle(long new_time) {
				if (new_time > old_time ) {
					old_time = new_time+(4*16700000);
					updateCanvas();
				}
	        }
	    };
	    
	    
		mainView = new app.view.Main(300, 300);
		myPet = new Pet("BALEK", App.getResource("res/skin.png"));
		
		updateCanvas();
		updateValue();
		
		timerCharacteristic.start();
		timerRefreshCanvas.start();
	}
	
	public void updateValue() {
		
		myPet.autoDecrement();
		mainView.changeName(myPet.getName());
		mainView.changeBladder(myPet.getBladder());
		mainView.changeEnergy(myPet.getEnergy());
		mainView.changeHydrated(myPet.getHydrated());
		mainView.changeHygiene(myPet.getHygiene());
		mainView.changeNourished(myPet.getNourished());
		mainView.changeSpirit(myPet.getSpirit());
		mainView.changeWeight(myPet.getWeight());
	}
	
	public void updateCanvas() {
		
		mainView.blanck();
		
		mainView.drawImage(myPet.getSkin(), myPet.getX(), myPet.getY());
		
		// juste un test d'affichage
		/*mainView.drawSprite(myPet.getSpritesImage(), myPet.getSprite("A").getMinX(), myPet.getSprite("A").getMinY(), 
													 myPet.getSprite("A").getWidth(), myPet.getSprite("A").getHeight());
		
		mainView.drawSprite(myPet.getSpritesImage(), myPet.getSprite("C").getMinX(), myPet.getSprite("C").getMinY(), 
				 									 myPet.getSprite("C").getWidth(), myPet.getSprite("C").getHeight());
		
		mainView.drawSprite(myPet.getSpritesImage(), myPet.getSprite("O").getMinX(), myPet.getSprite("O").getMinY(), 
				 									 myPet.getSprite("O").getWidth(), myPet.getSprite("O").getHeight());*/
	}
	
	public StackPane getRoot() {
		
		return mainView;
	}
}