package app.model;

public enum Language {
	french("français"),
	english("english"),
	chinese("chinois");
	
	//Langage actuel
	private static Language currentLanguage;
	
	//Texte du langage indépendemment de la langue utilisée.
	private String text;
	
	
	
	
	private Language(String text) {
		this.text=text;
	}
	
	
	//GETTERS---------------------------------------------------------------------------------------------
	public String call() {
		return text;
	}
	
	public static Language getCurrentLanguage() {
		return currentLanguage!=null?currentLanguage:french;
	}	
	
	//SETTERS---------------------------------------------------------------------------------------------
	
	public static void setCurrentLanguage(Language language) {
		currentLanguage=language;
	}

	
}
