package app.model;

import app.Componable;

/**
 * 
 * @author ben
 * Encapsule tous les élements du jeu
 */
public class Game implements Componable {
	 
	private Pet pet; //NOTE: référence partagé avec c.Pet
	private Room current_room; //NOTE: référence partagé avec c.Room
	private MiniGame current_minigame; //NOTE: référence partagé avec c.MiniGame
	
	public Game(Pet pet_instance, Room room_instance) {
		
		pet = pet_instance;
		current_room = room_instance;
			
		// le pet prend une douche
		pet.getHygiene().setBonus("shower", 10.0);
		
		// le pet mange
		pet.getWeight().setMalus("eat", 0.5);
		pet.getHunger().setBonus("eat", 2.5);
		
		// le pet boie
		pet.getThirst().setBonus("drink", 2.5);
		
		// le pet fait un mini-jeu
		pet.getWeight().setBonus("play", 1.5);
		pet.getThirst().setMalus("play", 0.05);
		pet.getHunger().setMalus("play", 0.05);
		pet.getMoral().setBonus("play", 1.5);
		
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
		
		// Mini jeu
		if ( pet.isPlaying() ) {
			if ( current_room.equals(Garden.getInstance()) ) {
				
				if ( current_minigame != null ) {
					
					if ( current_minigame.nextStep() ) {
						pet.applyEffect("play");
					}
					
					System.out.println(current_minigame.getInfo());
				}
				
			}
			else {
				pet.tooglePlaying();
				System.err.println("You can't play in the " + current_room);
			}
		}
		
		return !pet.isDead();
	}
	
	@Override
	public String toString() {
		return current_room.toString() + System.lineSeparator() + pet.toString();
	}
	
	@Override
	public void exit() {
		pet = null;
		current_room = null;
	}
}
