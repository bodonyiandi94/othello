package hu.unideb.inf.it.model;

/**
 * Egy, a mezőre helyezhető bábut leíró objektum.
 * 
 * @author Andi
 *
 */
public class Figure {
	FigureType figureType = FigureType.NONE;
	
	/**
	 * Visszaadja a figura típusát.
	 * 
	 * @return a figura típusa
	 */
	public FigureType getFigureType() {
		return figureType;
	}
	
	/**
	 * Beállítja a bábu típusát.
	 * 
	 * @param figureType a figura típusa
	 */
	public void setFigureType(FigureType figureType) {
		this.figureType = figureType;
	}
	
	/**
	 * Létrehoz egy új üres mezőt.
	 */
	public Figure() {
		
	}

	/**
	 * Létrehoz egy új bábut a megadott típussal
	 * 
	 * @param figureType a figura típusa
	 */
	public Figure(FigureType figureType) {
		super();
		this.figureType = figureType;
	}
}
