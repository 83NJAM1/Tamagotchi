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

	private Date currentDate;
	private File fileName;
	private FileWriter out;//FileOutputStream out;
	private FileReader in;//FileInputStream in;
	
	private Game game_record;
	private String loadDate;
	private String idRoom;
	private Hashtable<String, Double> stats;

	public Save(String pathname, Game game) {
		
		currentDate = new Date();
		stats = new Hashtable<String, Double>();
		
		if ( pathname != null ) {
			fileName = new File(pathname);
			try {
				out = new FileWriter(fileName);
				out.write(currentDate.toString()+System.lineSeparator());
				out.close();
			} catch ( IOException e) {
				System.err.println(e.getMessage());
			}
		}
		
		game_record = game;
	}
	
	public Double getStat(String key) {
		return stats.get(key);
	}
	/**
	 * JUSTE UN TEST PAS DEFINITIF
	 */
	public void save() {
		try {
			out = new FileWriter(fileName, true);
			out.write(game_record.toString());
			out.flush();
			out.close();
		} catch ( IOException e) {
			System.err.println(e.getMessage());
		}
	}
	
	/**
	 * JUSTE UN TEST PAS DEFINITIF
	 * @param info
	 */
	public String load(String pathname) {
		
		try {
			in = new FileReader(pathname);
			
			Scanner scanner = new Scanner(in);
			scanner.useLocale(Locale.ENGLISH);
			
			String key;
			
			loadDate = scanner.nextLine();
			idRoom = scanner.next();
			
			key = scanner.next();
				  scanner.next();
			stats.put(key, scanner.nextDouble());
			
			key = scanner.next();
	 			  scanner.next();
		 	stats.put(key, scanner.nextDouble());
			
			key = scanner.next();
		    	  scanner.next();
			stats.put(key, scanner.nextDouble());
			
			key = scanner.next();
			  	  scanner.next();
		  	stats.put(key, scanner.nextDouble());
			
			key = scanner.next();
			  	  scanner.next();
			stats.put(key, scanner.nextDouble());
			
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
