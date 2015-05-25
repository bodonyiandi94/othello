package hu.unideb.inf.it.controller;

/**
 * A játékot elindító osztály, amely tartalmazza a belépési pontot a Java virtuális gép számára.
 * 
 * @author Andi
 *
 */
public class Main {

	/**
	 * Az alkalmazás belépési pontja.
	 * 
	 * @param args a parancssori argumentumok
	 */
	public static void main(String[] args) {
		GameController.getInstance().start();
	}
}
