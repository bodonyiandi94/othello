package hu.unideb.inf.it.model;

/**
 * A játékosokat leíro objektum.
 * 
 * @author Andi
 *
 */
public class Player {
	private String name;
	FigureType figureType;
	
	/**
	 * Visszaadja a játékos nevét.
	 * 
	 * @return a játékos neve
	 */
	public String getName() {
		return name;
	}
	/**
	 * Beállítja a játékos nevét.
	 * 
	 * @param name a játékos neve
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * Visszaadja a játékoshoz tartozó figurák típusát.
	 * 
	 * @return a játékoshoz tartozó figurák típusa
	 */
	public FigureType getFigureType() {
		return figureType;
	}
	/**
	 * Beállítja a játékoshoz tartozó figurák típusát.
	 * 
	 * @param figureType a játékoshoz tartozó figurák típusa
	 */
	public void setFigureType(FigureType figureType) {
		this.figureType = figureType;
	}
	/**
	 * Létrehoz egy új játékost a megadott paraméterekkel.
	 * 
	 * @param name a játékos neve
	 * @param figureType a játékos figuráinak típusa
	 */
	public Player(String name, FigureType figureType) {
		super();
		this.name = name;
		this.figureType = figureType;
	}
	
	
	
}
