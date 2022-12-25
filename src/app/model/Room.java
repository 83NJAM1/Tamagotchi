package app.model;
 
/**
 * 
 * @author ben
 * Pièce dans laquelle le compagnon peut s'y rendre
 * Une pièce permet certaine action alors que d'autre non
 * 
 * Pour le moment une pièce se compose
 * uniquement d'un nom
 * 
 * Dans le cas de zone cliquable la pièce se composera
 * d'un nom et de zones
 */
public abstract class Room {
		
	private String id_name;
	private boolean initialized;
	
	public Room(String id_name) {
		this.id_name=id_name;
		initialized=false;
	}
	
	public abstract Room getAdjacent(Room room);
	public abstract boolean isAdjacent(Room room);
	
	public boolean equals(Room room) {
		return id_name.equals(room.id_name);
	}
	
	public boolean isInitialized() {
		return initialized;
	}
	public void initializationDone() {
		initialized=true;
	}
	public String getIdName() {
		return id_name;
	}
	
	@Override
	public String toString() {
		return getIdName();
	}
}
