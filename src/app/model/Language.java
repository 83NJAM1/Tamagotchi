package app.model;

public enum Language {
	french("français"),
	english("english"),
	chinese("chinois");
	
	private static Language currentLanguage;
	
	private String text;
	
	private Language(String text) {
		this.text=text;
	}
	
	public String call() {
		return text;
	}
	
	public static void setCurrentLanguage(Language language) {
		currentLanguage=language;
	}
	
	public static Language getCurrentLanguage() {
		return currentLanguage!=null?currentLanguage:french;
	}
	
}
