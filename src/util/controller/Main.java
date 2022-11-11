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
	private BoundingBox background;
	private BoundingBox happy1;
	private BoundingBox happy2;
	private BoundingBox happy3;
	private BoundingBox sad1;
	private BoundingBox sad2;
	private BoundingBox sad3;
	private BoundingBox petDestBox;
	private BoundingBox bgDestBox;
	private String spriteSheet;
	private String backgroundImg;
	private Sprite bgSprite;
	private AnimationTimer timerRefreshCanvas;
	
	public Main() {
		view = new util.view.Main();
		
		spriteSheet = "./res/Animation_Chat_Gros.png";
		backgroundImg = "./res/test_room.png";
		int frame_dim = 512;
		
		
		happy1 = new BoundingBox(0		  , 0		   , frame_dim, frame_dim);
		happy2 = new BoundingBox(frame_dim  , 0		   , frame_dim, frame_dim);
		happy3 = new BoundingBox(frame_dim*2, 0		   , frame_dim, frame_dim);
		sad1 = new BoundingBox(0		  , frame_dim*2, frame_dim, frame_dim);
		sad2 = new BoundingBox(frame_dim  , frame_dim*2, frame_dim, frame_dim);
		sad3 = new BoundingBox(frame_dim*2, frame_dim*2, frame_dim, frame_dim);
		petDestBox = new BoundingBox(0, 0, 256, 256);
		
		background = new BoundingBox(0, 0, 1024, 1024);
		bgDestBox = new BoundingBox(0, -96, 768, 768);
		
		printer = new PrinterSprite(64,64);
		
		Frame[] allframes = {new Frame(background, bgDestBox, backgroundImg, 1),
							 new Frame(happy1, petDestBox, spriteSheet, 1),
							 new Frame(happy2, petDestBox, spriteSheet, 1),
							 new Frame(happy3, petDestBox, spriteSheet, 1),
							 new Frame(sad1, petDestBox, spriteSheet, 1),
							 new Frame(sad2, petDestBox, spriteSheet, 1),
							 new Frame(sad3, petDestBox, spriteSheet, 1)};
		
		Anime[] allanimes = {new Anime("happy"), new Anime("sad")};
		Anime bgAnime = new Anime("bg");
		
		bgAnime.addFrame(allframes[0]);
		allanimes[0].addFrame(allframes[1], allframes[2], allframes[3]);
		allanimes[1].addFrame(allframes[4], allframes[5], allframes[6]);
		
		animes = new AnimeList();
		
		animes.addItems(allanimes);
		
		bgSprite = new Sprite();
		
		bgSprite.addAnime(bgAnime);
		bgSprite.setAnime("bg");
		bgSprite.setSpriteSheet(backgroundImg);
		
		System.out.println("bodysprite-anime:"+bgSprite.getAnime());
		System.out.println("bodysprite-source:"+bgSprite.getSourceBox());
		System.out.println("bodysprite-source:"+bgSprite.getCurrentTime());
		
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
		bgSprite.run(0);
		bgSprite.play();
		printer.draw(bgSprite, true);
		view.update();
		printer.update();
	}
	
	public Parent getView() {
		return view;
	}
}
