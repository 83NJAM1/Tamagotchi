package app.model;

/*
 * Un Mood résume l'état ou l'activité actuelle du pet.
 * Il aide aussi le contrôleur à décider quelles animations jouer.
 */

public enum Mood {
	idle,
	sleeping(2),
	cooking(3),
	dining(4),
	dead(0);
	
	//Permet l'interaction logique de plusieurs Mood (voir Pet)
	public int family;
	
	private Mood(int family) {
		this.family=family;
	}
	
	private Mood() {
		this(1);
	}
	
	public boolean sameFamily(Mood mood) {
		return mood.family==this.family;
	}
	
}
