package app.model;

import java.util.Random;

import app.Cleanable;

/**
 * 
 * @author ben
 * Encapsule tous les élements du jeu
 */
public class Game implements Cleanable {
	 
	private Pet pet; //NOTE: référence partagé avec c.Pet
	private Room current_room; //NOTE: référence partagé avec c.Room
	private MiniGame current_minigame; //NOTE: référence partagé avec c.MiniGame
	private int current_indexWeather;
	private String[] weathers;
	private Random weatherSeed;
	
	public Game(Pet pet_instance, Room room_instance) {
		
		pet = pet_instance;
		current_room = room_instance;
			
		// le pet prend une douche
		pet.getHygiene().setBonus("shower", 100.0);
		
		// le pet mange
		pet.getWeight().setBonus("eat", 0.5); //grossi
		pet.getHunger().setBonus("eat", 20.5);
		
		// le pet boie
		pet.getThirst().setBonus("drink", 20.5);
		
		// le pet fait un mini-jeu
		pet.getWeight().setMalus("play", 1.5); //maigri
		pet.getThirst().setMalus("play", 0.05);
		pet.getHunger().setMalus("play", 0.05);
		pet.getMoral().setBonus("play", 1.5);
		
		// effet météo
		
		// galcial
		pet.getMoral().setMalus("icy", 0.05);
		pet.getHunger().setMalus("icy", 0.05);
		// caniculaire
		pet.getMoral().setMalus("scorchy", 0.05);
		pet.getThirst().setMalus("scorchy", 0.05);
		// pluivieu
		pet.getMoral().setMalus("rainy", 0.05);
		pet.getHygiene().setMalus("rainy", 0.05);
		// ensoleillé
		pet.getMoral().setBonus("suny", 0.05);
		// nuageu
		// rien de particulier
		// orageu
		// pet fait des cauchemards
		
		weathers = new String[] {"rainy", "cloudy", "suny", "stormy", "scorchy", "icy"};
		weatherSeed = new Random();
		nextWeather();
		
		/**
		 * construit la maison en initialisant les pièces
		 * 
		 * 	          Kitchen
		 * 				 ·
		 *               |
		 *               ·
		 * Garden ·--· Livingroom ·--· Bedroom
		 * 				 ·			   ___·
		 * 	             |         ___/ 
		 *               |     ___/
		 *               | ___/
		 *               |/
		 *               ·
		 *            Bathroom
         */
		Garden.getInstance().init(Livingroom.getInstance());
		Bedroom.getInstance().init(Livingroom.getInstance(), Bathroom.getInstance());
		Livingroom.getInstance().init(Kitchen.getInstance(), Garden.getInstance(), Bathroom.getInstance(), Bedroom.getInstance());
		Bathroom.getInstance().init(Livingroom.getInstance(), Bedroom.getInstance());
		Kitchen.getInstance().init(Livingroom.getInstance());
	}
	
	public Pet getModelPet() {
		return pet;
	}
	
	public Room getCurrentModelRoom() {
		return current_room;
	}
	
	public boolean setCurrentModelRoom(Room room) {
		
		if ( current_room.equals(room) ) {
			System.err.println("You already are in " + current_room);
			return false;
		}
		else if ( current_room.isAdjacent(room) ) {
			System.out.println("Go to " + room + " from " + current_room);
			current_room = current_room.getAdjacent(room);
			return true;
		}
		else {
			System.err.println("You can't go to " + room + " from " + current_room);
			return false;
		}
	}
	
	public void setMiniGame(MiniGame minigame) {
		current_minigame = minigame;
	}
	
	/**
	 * applique les règles.
	 * determine l'état suivant du jeu depuis son état actuel.
	 */
	public boolean nextStep() {
		
		if ( !pet.isSleeping() ) {
			// états
			if ( pet.isTakingShower() ) {
				if ( current_room.equals(Bathroom.getInstance()) ) {
					pet.applyEffect("shower");
					pet.toogleTakingShower();
				}
				else {
					pet.toogleTakingShower();
					System.err.println("You can't take shower in the " + current_room);
				}
			}
			if ( pet.isEating() ) {
				if ( current_room.equals(Kitchen.getInstance()) ) {
					pet.applyEffect("eat");
					pet.toogleEating();
				}
				else {
					pet.toogleEating();
					System.err.println("You can't eat in the " + current_room);
				}
			}
			if ( pet.isDrinking() ) {
				pet.applyEffect("drink");
				pet.toogleDrinking();
			}
			
			// apllique les effets météo seulement en extérieure
			if ( current_room.equals(Garden.getInstance()) ) {
				
				pet.applyEffect( weathers[current_indexWeather] );
			}
			
			// Mini jeu
			if ( pet.isPlaying() ) {
				if ( current_room.equals(Garden.getInstance()) && current_minigame != null) {
					
					if ( pet.wantPlay() ) {
						
						if ( current_minigame.nextStep() ) {
							pet.applyEffect("play");
							System.out.println(current_minigame.getInfo());
						}
						
					}
					else {
						pet.tooglePlaying();
						pet.setCatch(false);
						pet.setFetch(false);
						System.out.println("the " + pet.getType() + " want to stop ");
					}
					
				}
				else {
					pet.tooglePlaying();
					pet.setCatch(false);
					pet.setFetch(false);
					System.err.println("You can't play in the " + current_room + " at " + current_minigame );
				}
			}
			pet.getEnergy().applyMalus("sleep");
		}
		else {
			System.out.println(pet.getEnergy().getValue());
			pet.getEnergy().applyBonus("sleep");
		}
		return !pet.isDead();
	}
	
	public void nextWeather() {
		current_indexWeather = weatherSeed.nextInt(0, weathers.length);
	}
	
	public String getWeather() {
		return weathers[current_indexWeather];
	}
	public int getWeatherIndex() {
		return current_indexWeather;
	}
	
	@Override
	public String toString() {
		return current_room.toString() + System.lineSeparator() + pet.toString();
	}
	
	@Override
	public void clean() {
		pet = null;
		current_room = null;
	}
}
