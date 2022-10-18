package app.model;

import java.util.Arrays;
import java.util.List;


public enum Specie {
	dog("#ffffff","#ffffff","#ffffff"),
	cat("#ffffff","#ffffff","#ffffff"),
	rabbit("#ffffff","#ffffff","#ffffff"),
	robot("#ffffff","#ffffff","#ffffff"),
	;
	
	private String name;
	
	private Pattern pattern;
	
	private String firstColor;
	private String secondColor;
	private String thirdColor;
	
	private Specie(String firstColor,String secondColor,String thirdColor) {
		this.pattern=Pattern.monocolor;
		this.firstColor=firstColor;
		this.secondColor=secondColor;
		this.thirdColor=thirdColor;
		}
	
	public void setName(String name) {
		this.name=name;
	}
	public String getName() {
		if(name==null) {
			switch(this) {
				case dog : return Text.dog_basename.call();
				case cat : return Text.cat_basename.call();
				case rabbit : return Text.rabbit_basename.call();
				case robot : return Text.robot_basename.call();
			}
		}
		return name;
	}
	
	public void setPattern(Pattern pattern) {
		this.pattern=pattern;
	}
	public Pattern getPattern() {
		return pattern;
	}
	
	public void setFirstColor(String color) {
		firstColor=color;
	}
	public String getFirstColor() {
		return firstColor;
	}
	
	public void setSecondColor(String color) {
		secondColor=color;
	}
	public String getSecondColor() {
		return secondColor;
	}
	
	public void setThirdColor(String color) {
		thirdColor=color;
	}
	public String getThirdColor() {
		return thirdColor;
	}
	
	public List<String> getBestFirstColors(){
		switch(this) {
		
			case dog:return Arrays.asList("#ffffff","#ffffff","#ffffff","#ffffff","#ffffff");
			case cat:return Arrays.asList("#ffffff","#ffffff","#ffffff","#ffffff","#ffffff");
			case rabbit:return Arrays.asList("#ffffff","#ffffff","#ffffff","#ffffff","#ffffff");
			default:
			case robot:return Arrays.asList("#ffffff","#ffffff","#ffffff","#ffffff","#ffffff");
			
		}
	}
	
	public List<String> getBestSecondColors(){
		switch(this) {
		
			case dog:return Arrays.asList("#ffffff","#ffffff","#ffffff","#ffffff","#ffffff");
			case cat:return Arrays.asList("#ffffff","#ffffff","#ffffff","#ffffff","#ffffff");
			case rabbit:return Arrays.asList("#ffffff","#ffffff","#ffffff","#ffffff","#ffffff");
			default:
			case robot:return Arrays.asList("#ffffff","#ffffff","#ffffff","#ffffff","#ffffff");
			
		}
	}
	
	public List<String> getBestThirdColors(){
		switch(this) {
		
			case dog:return Arrays.asList("#ffffff","#ffffff","#ffffff","#ffffff","#ffffff");
			case cat:return Arrays.asList("#ffffff","#ffffff","#ffffff","#ffffff","#ffffff");
			case rabbit:return Arrays.asList("#ffffff","#ffffff","#ffffff","#ffffff","#ffffff");
			default:
			case robot:return Arrays.asList("#ffffff","#ffffff","#ffffff","#ffffff","#ffffff");
			
		}
	}
	
	
	
	public List<Pattern> getPatterns() {
		
		List<Pattern> patterns = Arrays.asList(Pattern.monocolor,Pattern.bicolor,Pattern.tricolor);
		
		switch(this) {
			case cat:patterns.add(Pattern.tabby);
				break;
			default:
		}
		
		return patterns;
	}
	
	
}
