package hu.unideb.inf.it.model;

/**
 * A jatekosokat leiro objektum.
 * 
 * @author Andi
 *
 */
public class Player {
	private String name;
	FigureType figureType;
	
	/**
	 * Visszaadja a jatekos nevet.
	 * 
	 * @return a jatekos neve
	 */
	public String getName() {
		return name;
	}
	/**
	 * Beallitja a jatekos nevet.
	 * 
	 * @param name a jatekos neve
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * Visszaadja a jatekoshoz tartozo figurak tipusat.
	 * 
	 * @return a jatekoshoz tartozo figurak tipusa
	 */
	public FigureType getFigureType() {
		return figureType;
	}
	/**
	 * Beallitja a jatekoshoz tartozo figurak tipusat.
	 * 
	 * @param figureType a jatekoshoz tartozo figurak tipusa
	 */
	public void setFigureType(FigureType figureType) {
		this.figureType = figureType;
	}
	/**
	 * Letrehoz egy uj jatekost a megadott parameterekkel.
	 * 
	 * @param name a jatekos neve
	 * @param figureType a jatekos figurainak tipusa
	 */
	public Player(String name, FigureType figureType) {
		super();
		this.name = name;
		this.figureType = figureType;
	}
	
	
	
}
