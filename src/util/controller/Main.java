package util.controller;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.geometry.BoundingBox;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class Main {
	private util.view.Main view;
	private PrinterSprite printer;
	private AnimeList animes;
	private BoundingBox bodyBox;
	private BoundingBox headABox;
	private BoundingBox headBBox;
	private BoundingBox headCBox;
	private BoundingBox headDBox;
	private BoundingBox headEBox;
	private BoundingBox headFBox;
	private BoundingBox headDestBox;
	private BoundingBox bodyDestBox;
	private String spriteSheet;
	private Sprite body;
	private AnimationTimer timerRefreshCanvas;
	
	public Main() {
		view = new util.view.Main();
		
		spriteSheet = "./res/PET_TAMAGOCHI.png";
		bodyBox = new BoundingBox(0, 0, 512, 512);
		headABox = new BoundingBox(512, 0, 512, 512);
		headBBox = new BoundingBox(512*2, 0, 512, 512);
		headCBox = new BoundingBox(512*3, 0, 512, 512);
		headDBox = new BoundingBox(0, 512, 512, 512);
		headEBox = new BoundingBox(512, 512, 512, 512);
		headFBox = new BoundingBox(512*2, 512, 512, 512);
		headDestBox = new BoundingBox(0, 0, 64, 64);
		bodyDestBox = new BoundingBox(0, 32, 64, 64);
		printer = new PrinterSprite(150,150);
		
		Frame[] allframes = {new Frame(bodyBox, bodyDestBox, spriteSheet, 1),
							 new Frame(headABox, headDestBox, spriteSheet, 2),
							 new Frame(headBBox, headDestBox, spriteSheet, 3),
							 new Frame(headCBox, headDestBox, spriteSheet, 4),
							 new Frame(headDBox, headDestBox, spriteSheet, 5),
							 new Frame(headEBox, headDestBox, spriteSheet, 6),
							 new Frame(headFBox, headDestBox, spriteSheet, 7)};
		
		Anime[] allanimes = {new Anime("sad"), new Anime("happy")};
		Anime bodyAnime = new Anime("body");
		
		bodyAnime.addFrame(allframes[0]);
		allanimes[0].addFrame(allframes[1], allframes[2], allframes[3]);
		allanimes[1].addFrame(allframes[4], allframes[5], allframes[6]);
		
		animes = new AnimeList();
		
		animes.addItems(allanimes);
		
		body = new Sprite();
		
		body.addAnime(bodyAnime);
		body.setAnime("body");
		body.setSpriteSheet(spriteSheet);
		
		System.out.println("bodysprite-anime:"+body.getAnime());
		System.out.println("bodysprite-source:"+body.getSourceBox());
		System.out.println("bodysprite-source:"+body.getCurrentTime());
		
		Sprite sprite = new Sprite();
		
		sprite.addAnime(allanimes[0]);
		sprite.addAnime(allanimes[1]);
		sprite.setAnime("happy");
		
		printer.setSprite(sprite);
		
		view.getLeft().getChildren().add(printer.getView());
		view.getRight().getChildren().add(animes.getView());		
		
	    timerRefreshCanvas = new AnimationTimer() {
			long old_time=0;
			@Override
	        public void handle(long new_time) {
				if (new_time > old_time ) {
					old_time = new_time+(64*16700000);
					sprite.run(0);
					update();
				}
	        }
	    };
	    
		EventHandler<MouseEvent> list_event = new EventHandler<MouseEvent>() {
			FrameList old = null;
			
			public void handle(MouseEvent e) {
				
				for (Anime a : animes.getAnimes()) {
					
					System.out.println(a.getNameId());
					if (a.getNameId() == animes.getView().getSelectedItem()) {
												
						if ( ! view.getRight().getChildren().contains(a.getFrames().getView()) ) {
							
							if ( old != null ) {
								view.getRight().getChildren().remove(old.getView());
							}
							
							if (a.getCurrentFrame() != null) {
								sprite.setAnime(a.getNameId());
							}
							
							update();
							printer.checkAnimePause();
							view.getRight().getChildren().add(a.getFrames().getView());
							old = a.getFrames();
						}
					}
				}
			}
		};
		
		timerRefreshCanvas.start();
		animes.setOnClick(list_event);
		
	}
	public void update() {
		
		printer.blank();
		body.run(0);
		body.play();
		printer.draw(body, true);
		view.update();
		printer.update();
	}
	
	public Parent getView() {
		return view;
	}
}
