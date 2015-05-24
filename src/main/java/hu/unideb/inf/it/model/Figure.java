package hu.unideb.inf.it.model;

/**
 * Egy, a mezore helyezheto babut leiro objektum.
 * 
 * @author Andi
 *
 */
public class Figure {
	FigureType figureType = FigureType.NONE;
	
	/**
	 * Visszaadja a figura tipusat.
	 * 
	 * @return a figura tipusa
	 */
	public FigureType getFigureType() {
		return figureType;
	}
	
	/**
	 * Beallitja a babu tipusat.
	 * 
	 * @param figureType a figura tipusa
	 */
	public void setFigureType(FigureType figureType) {
		this.figureType = figureType;
	}
	
	/**
	 * Letrehoz egy uj ures mezot.
	 */
	public Figure() {
		
	}

	/**
	 * Letrehoz egy uj babut a megadott tipussal
	 * 
	 * @param figureType a figura tipusa
	 */
	public Figure(FigureType figureType) {
		super();
		this.figureType = figureType;
	}
}
