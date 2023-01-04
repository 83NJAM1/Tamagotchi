package app.model;

/**
 * 
 * @author ben
 *
 */
public abstract class MiniGame {

	private String id_name;
	
	public MiniGame(String name) {
		id_name = name;
	}
	
	public String getIdName() {
		return id_name;
	}
	
	@Override
	public String toString() {
		return getIdName();
	}
	
	public abstract boolean nextStep();
	public abstract String getInfo();
}
