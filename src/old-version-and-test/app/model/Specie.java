package app.model;

import java.util.Arrays;
import java.util.List;

/*
 * Espèce.
 * 
 * La forme Enum a les avantages de pouvoir se switch proprement, de simplifier la lisibilité du modèle et de simplifier le fonctionnement
 * interne de la création de Pet.
 * 
 * Dans le tamagoshi, l'espèce n'est qu'une caractéristique comme une autre, qui a même moins d'infuence que la Room par exemple.
 * 
 * Créer plusieurs sous-classes de Pet sous prétexte que son espèce ne peut pas changer est possible, intuitif d'un point de vue humain,
 *         équivalent d'un point de vue fonctionnel, mais lourd dans la pratique.
 *         
 */

public enum Specie {
	dog("#ffffff","#ffffff","#ffffff"),
	cat("#ffffff","#ffffff","#ffffff"),
	rabbit("#ffffff","#ffffff","#ffffff"),
	robot("#ffffff","#ffffff","#ffffff"),
	;
	
	//Espèce par défaut lors de la création de Pet
	private static Specie defaultSpecie;
	
	//Nom par défaut de l'espèce
	private String name;
	
	//Pattern par défaut de l'espèce
	private Pattern pattern;
	
	//Couleurs par défaut
	private String firstColor;
	private String secondColor;
	private String thirdColor;
	
	

	private Specie(String firstColor,String secondColor,String thirdColor) {
		this.pattern=Pattern.monocolor;
		this.firstColor=firstColor;
		this.secondColor=secondColor;
		this.thirdColor=thirdColor;
		}
	
	
	
	
	//GETTERS---------------------------------------------------------------------------------------------

	public String getName() {
		if(name==null) return getDefaultName();
		return name;
	}
	
	public static Specie getDefault() {
		return (defaultSpecie==null)?cat:defaultSpecie;
	}	
	
	public Pattern getPattern() {
		return pattern;
	}

	public String getFirstColor() {
		return firstColor;
	}

	public String getSecondColor() {
		return secondColor;
	}
	
	public String getThirdColor() {
		return thirdColor;
	}
	
	
	//SETTERS---------------------------------------------------------------------------------------------
	
	public void setName(String name) {
		this.name=name;
	}
	public static void setDefault(Specie specie) {
		defaultSpecie=specie;
	}

	
	public void setPattern(Pattern pattern) {
		this.pattern=pattern;
	}

	
	public void setFirstColor(String color) {
		firstColor=color;
	}

	
	public void setSecondColor(String color) {
		secondColor=color;
	}

	
	public void setThirdColor(String color) {
		thirdColor=color;
	}
	
	
	
	//Getter des noms par défaut
	
	public String getDefaultName() {
		switch(this) {
				case dog : return Text.dog_basename.call();
				case cat : return Text.cat_basename.call();
				case rabbit : return Text.rabbit_basename.call();
				default:
				case robot : return Text.robot_basename.call();
			}
		
	}
	
	//Getters des couleurs conseillées

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
	
	
	
	//Getter des patterns autorisés
	
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
