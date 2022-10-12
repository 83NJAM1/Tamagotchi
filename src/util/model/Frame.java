package util.model;

import javafx.geometry.BoundingBox;

public class Frame {
	private BoundingBox source;
	private BoundingBox destination;
	private String pathSpriteSheet;
	private long time;
	
	public Frame (BoundingBox src, BoundingBox dest, String path, long time) {
		setSource(src);
		setDestination(dest);
		setSpriteSheet(path);
		setTime(time);
	}
	
	public void setSource(BoundingBox source) {
		this.source = source;
	}
	
	public void setDestination(BoundingBox dest) {
		this.destination = dest;
	}
	
	public void setSpriteSheet(String pathSpriteSheet) {
		this.pathSpriteSheet = pathSpriteSheet;
	}
	
	public void setTime(long time) {
		this.time = time;
	}
	
	public BoundingBox getSource() { 
		return source; 
	}
	
	public BoundingBox getDestination() { 
		return destination; 
	}
	
	public String getSpriteSheet() { 
		return pathSpriteSheet;
	}
	
	public long getTime() { 
		return time; 
	}
	
}
