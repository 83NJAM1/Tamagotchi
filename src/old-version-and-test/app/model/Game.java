package app.model;

import java.util.List;
import java.util.Scanner;

import java.util.ArrayList;
import java.util.Arrays;

/*
 * Colonne vertébrale du modèle.
 * 
 * C'est avec elle que communique le contrôlleur pour obtenir des informations.
 */

public class Game {
	
	//Ne sert actuellement qu'à autoriser les actions "FastForward"
	private static final boolean devMode = true;
	
	//Etat global actuel
	private static Stage stage = Stage.title;
	
	//Liste des pets possédés
	private static List<Pet> pets = new ArrayList<Pet>();
	
	//Pet actuel
	private static Pet selectedPet;
	
	
	
	
	public static void refresh() {
		if(is(Stage.playing)) selectedPet.refresh();
	}
	
	
	//GETTERS---------------------------------------------------------------------------------------------
	
	public static Stage getStage() {
		return stage;
	}
	
	public static List<Action> getEnabledActions() {
		List<Action> list = new ArrayList<Action>();
		
		for(Action action : Action.values()) 
				if(action.isAllowed())
					list.add(action);
					
		return list;
	}
	
	public static Pet getSelectedPet() {
		return selectedPet;
	}
	
	public static List<Pet> getPetList(){
		return pets;
	}
	
	public static boolean isDevMode() {
		return devMode;
	}
	
	public static boolean is(Stage stage) {
		return Game.stage==stage;
	}
	
	
	//SETTERS---------------------------------------------------------------------------------------------
	public static void setStage(Stage stage) {
		Game.stage=stage;
	}
	
	public static void addPet(Pet pet) {
		pets.add(pet);
		setSelectedPet(pet);
	}
	
	public static void removePet(Pet pet) {
		if(selectedPet==pet) {
			int i = pets.indexOf(selectedPet);
			pets.remove(pet);
			setSelectedPet(pets.size()==0?null:pets.get((i+pets.size()-1)%pets.size()));
		}
		else pets.remove(pet);
		
	}
	
	public static void setSelectedPet(Pet pet) {
		selectedPet=pet;
	}

	
	
	
	
	
	/*
	 * Fonctions de Test permettant de faire tourner le modèle indépendemment du contrôleur et de la vue.
	 * grace à un pseudo-contrôleur (boucle) et une pseudo-vue (console et scanner)
	 */
	
	private static final List<String> inputTable = Arrays.asList("0","1","2","3","4","5","6","7","8","9","a","b","c","d","e","f","g","h","i","j","k","l");
	
	public static String getTextInfo() {
		
		String s="-----------------------------------------------------"+stage.name().toUpperCase()
				+"-----------------------------------------------------\n";
				
				if(is(Stage.playing)) s+=selectedPet.getTextinfo();
				
				return s;
				
	}
	
	public static void showTextInfo() {
		
		String s=getTextInfo();
				
		
		List<Action> actions = getEnabledActions();

		int n=0;
		for(Action a:actions){
			s+=inputTable.get(n)+" : "+Text.call(a)+"\t\t";
			n++;
		}
		
		System.out.println(s);
				
	}
	
	public static void playWithConsole() {
		Scanner scanner = new Scanner(System.in);
		
		for(int i=0;i<1000000;i++) {
			refresh();
			showTextInfo();
		
			String input = scanner.nextLine();
			int n = 0;
			if(input.length()==2) n = input.charAt(1)-'0';
		
			Action action=getEnabledActions().get(inputTable.indexOf(String.valueOf(input.charAt(0))));
			refresh();
			Action.setContext(n);
			action.execute();
		}
		scanner.close();
		
	}
	
	public static void main(String[] args) {
		playWithConsole();
	}

}
