package app;

public interface Reinstanciable {
	
	/**
	 * etat de fin. Stop tous les threads, met ses enfant dans le même état de fin, met tous à null.
	 * Le but étant d'être dans un état cohérent lors d'une réinitialisation.
	 * Ainsi que d'aidé au mieux le JVM pour la libération de mémoire.
	 */
	public void exit();
}
