package app.model;

/**
 * 
 * @author ben
 * Encapsule tous les élements du jeu
 */
public class Game {
	
	// ATTENTION: référence partagé avec controller.Pet
	private Pet pet;
	
	// ATTENTION: référence partagé avec controller.Room
	private Room room;
	
	public Game(Pet pet_instance, Room room_instance) {
		this.pet = pet_instance;
		this.room = room_instance;
	}
	
	public Pet getPet() {
		return pet;
	}
	
	public Room getRoom() {
		return room;
	}
	public void setRoom(Room new_room) {
		room = new_room;
	}
	
	public String toString() {
		return room.toString() + System.lineSeparator() + pet.toString();
	}
}
