package app.model;
 
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.Hashtable;
import java.util.Locale;
import java.util.Scanner;

/**
 * 
 * @author ben
 * Permet de sauvegarder un état données du jeu
 */
public class Save {

	private File file;
	
	private Game game_record;
	private String loadDate;
	private String idRoom;
	private int numberStats;
	private Hashtable<String, Double> stats;

	public Save(String pathname, Game game) {

		stats = new Hashtable<String, Double>();
		
		try {
			file = new File(pathname);
		} 
		catch ( NullPointerException e) {
			System.err.println(e.getMessage());
		}
		
		game_record = game;
	}
	
	public Double getStat(String key) {
		return stats.get(key);
	}
	
	/**
	 * PAS DEFINITIF
	 */
	public void save() {
		try {
			FileWriter out = new FileWriter(file);
			out.write( (new Date()).toString() + System.lineSeparator() + game_record.toString() );
			out.flush();
			out.close();
		} catch ( IOException e) {
			System.err.println(e.getMessage());
		}
	}
	
	/**
	 * PAS DEFINITIF
	 * @param pathname
	 */
	public String load(String pathname) {
		
		try {
			FileReader in = new FileReader(pathname);
			
			Scanner scanner = new Scanner(in);
			scanner.useLocale(Locale.ENGLISH);
			
			loadDate = scanner.nextLine();
			idRoom = scanner.next();
			numberStats = scanner.nextInt();
			
			String key;
			for ( int i=0; i<numberStats; i++) {
				key = scanner.next();
					  scanner.next();
				stats.put(key, scanner.nextDouble());
			}
			
			in.close();
			scanner.close();
				
		} catch ( IOException e) {
			System.err.println("unknown error : " + e);
		} catch (IndexOutOfBoundsException e ) {
			System.err.println("end of file : " + e);
		}
				
		if ( game_record != null ) {
			game_record.getPet().getHunger().setValue(stats.get("hunger"));
			game_record.getPet().getThirst().setValue(stats.get("thirst"));
			game_record.getPet().getWeight().setValue(stats.get("weight"));
			game_record.getPet().getHygiene().setValue(stats.get("hygiene"));
			game_record.getPet().getMoral().setValue(stats.get("moral"));
		}
		
		return loadDate + System.lineSeparator()
			 + idRoom + System.lineSeparator()
			 + stats.get("hunger") + System.lineSeparator()
			 + stats.get("thirst") + System.lineSeparator()
			 + stats.get("weight") + System.lineSeparator()
			 + stats.get("hygiene") + System.lineSeparator()
			 + stats.get("moral") + System.lineSeparator();
	}
}
