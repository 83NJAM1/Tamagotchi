package app.model;

public enum Mood {
	idle,
	sleeping(2),
	cooking(3),
	dining(4),
	dead(0);
	
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
