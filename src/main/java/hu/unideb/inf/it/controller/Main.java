package hu.unideb.inf.it.controller;

/**
 * A jatekot elindito osztaly, amely tartalmazza a belepesi pontot a Java virtualis gep szamara.
 * 
 * @author Andi
 *
 */
public class Main {

	/**
	 * Az alkalmazas belepesi pontja.
	 * 
	 * @param args a parancssori argumentumok
	 */
	public static void main(String[] args) {
		GameController.getInstance().start();
	}
}
