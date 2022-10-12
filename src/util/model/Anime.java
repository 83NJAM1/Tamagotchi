package util.model;

import java.util.Vector;

import javafx.geometry.BoundingBox;

public class Anime {
	private Vector<Frame> frames;
	private String id;
	private int currentFrame;
	private long time;
	private long globalTime;
	private long maxTime;
	private boolean played;
	
	// NOTE delete
	long startTime = 0;
	// end delete
	
	public Anime(String id) {
		this.id = id;
		frames = new Vector<Frame>();
		currentFrame = 0;
		time = 0;
		played = false;
	}
	
	public Vector<Frame>  getFrames() {
		return frames;
	}
	
	public void addFrame(Frame new_frame) {
		if ( new_frame != null ) {
			frames.add(new_frame);
			maxTime+=new_frame.getTime();
			System.out.println(new_frame.getTime() + " added to " + this );
		}
	}
	
	public void removeFrame(int i) {
		if ( i >= 0 && i < frames.size()) {
			maxTime-=frames.get(i).getTime();
			frames.remove(i);
		}
		System.out.println("frame Deleted");
	}
	
	private void nextFrame() {
		if ( currentFrame < frames.size()-1) {
			currentFrame++;
			startTime = System.currentTimeMillis();
		}
		else {
			currentFrame = 0;
		}
		time = 1;
	}
	
	private void incTime() {
		if ( time < maxTime) {
			time++;
		}
		else {
			time = 1;
		}
	}
	
	public long getGlobalTime() {
		return globalTime;
	}
	
	public long getMaxTime() {
		return maxTime;
	}
	
	public long getCurrentTime() {
		return time;
	}
	
	public int getCurrentFrame() {
		return currentFrame;
	}
	
	public String getSpriteSheet() {
		try {
			return frames.get(currentFrame).getSpriteSheet();
		}
		catch(ArrayIndexOutOfBoundsException e){
			System.err.println("Erreur pour: " + id + "\n" + e);
			//System.exit(0);
			return null;
		}
	}
	
	public BoundingBox getSourceBox() {
		try {
			return frames.get(currentFrame).getSource();
		}
		catch(ArrayIndexOutOfBoundsException e){
			System.err.println("Erreur pour: " + id + "\n" + e);
			//System.exit(0);
			return null;
		}
	}
	
	public BoundingBox getDestBox() {
		try {
			return frames.get(currentFrame).getDestination();
		}
		catch(ArrayIndexOutOfBoundsException e){
			System.err.println("Erreur pour: " + id + "\n" + e);
			//System.exit(0);
			return null;
		}
	}
	
	public Frame run() {
		if ( played && !frames.isEmpty()) {
			
			if ( currentFrame < frames.size() ) {
				
				// NOTE delete
				if(startTime != 0) {
					//System.out.println("time:" + currentFrame + "->" + (System.currentTimeMillis()-startTime)/1000);
				}
				// end delete
				
				if ( frames.get(currentFrame).getTime() <= time ) {
					this.nextFrame();
				}
				else {
					this.incTime();
				}
				
				if ( globalTime < maxTime) {
					globalTime++;
				}
				else {
					globalTime = 1;
				}
				
			}
			else {
				currentFrame = 0;
			}
		}
		return null;
	}
	
	public void play() {
		played = true;
	}
	
	public void pause() {
		played = false;
	}
	
	public boolean isPaused() {
		return !played;
	}
	
	public String getId() {
		return id;
	}
}
