package app.model;

import java.util.List;
import java.util.Scanner;

import java.util.ArrayList;
import java.util.Arrays;

public class Game {
	
	private static final boolean devMode = true;
	
	private static Stage stage = Stage.title;
	
	private static List<Pet> pets = new ArrayList<Pet>();
	private static Pet selectedPet;
	
	private static Specie specie = Specie.cat;

	public static void refresh() {
		if(is(Stage.playing)) selectedPet.refresh();
	}
	
	public static boolean isDevMode() {
		return devMode;
	}
	
	public static void setStage(Stage stage) {
		Game.stage=stage;
	}
	
	public static boolean is(Stage stage) {
		return Game.stage==stage;
	}
	
	public static void addPet(Pet pet) {
		pets.add(pet);
		setSelectedPet(pet);
	}
	
	public static void removePet(Pet pet) {
		if(selectedPet==pet) {
		int i = pets.indexOf(selectedPet);
		pets.remove(pet);
		setSelectedPet(pets.size()==0?null:pets.get((i+pets.size()-1)%pets.size()));}
		else pets.remove(pet);
		
	}
	
	public static List<Pet> getPetList(){
		return pets;
	}
	
	public static void setSelectedPet(Pet pet) {
		selectedPet=pet;
		Action.setContextPet(pet);
	}
	
	public static Pet getSelectedPet() {
		return selectedPet;
	}
	
	public static void setSpecie(Specie specie) {
		Game.specie=specie;
	}
	
	public static Specie getSpecie() {
		return specie;
	}
	
	public static List<Action> getEnabledActions() {
		List<Action> list = new ArrayList<Action>();
		
		for(Action action : Action.values()) 
				if(action.isAllowed())
					list.add(action);
					
		return list;
	}
	
	private static final List<String> inputTable = Arrays.asList("0","1","2","3","4","5","6","7","8","9","a","b","c","d","e","f","g","h","i","j","k","l");
	
	public static void getTextInfo() {
				
		System.out.println("-----------------------------------------------------"+stage.name().toUpperCase()
		+"-----------------------------------------------------");
		
		if(is(Stage.playing)) selectedPet.getTextinfo();
		
		List<Action> actions = getEnabledActions();
		
		String s="";
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
			getTextInfo();
		
		String input = scanner.nextLine();
		int n = 0;
		if(input.length()==2) n = input.charAt(1)-'0';
		
		Action action=getEnabledActions().get(inputTable.indexOf(String.valueOf(input.charAt(0))));
		refresh();
		action.execute(n);
		}
		scanner.close();
		
	}
	
	public static void main(String[] args) {
		playWithConsole();
	}

}
