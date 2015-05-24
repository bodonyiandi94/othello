package hu.unideb.inf.it.model;

/**
 * A jatek altal hasznalt babuk tipusa.
 * 
 * @author Andi
 *
 */
public enum FigureType {
	/**
	 * Fekete szinu babu
	 */
	BLACK("figure_black.png"), 
	
	/**
	 * Feher szinu babu
	 */
	WHITE("figure_white.png"), 
	
	/**
	 * Ures mezo
	 */
	NONE("figure_empty.png");
	
	private String texturePath;
	
	/**
	 * Visszaadja a babutipus altal hasznalt textura eleresi utjat.
	 * 
	 * @return a textura eleresi utvonala
	 */
	public String getTexturePath() {
		return texturePath;
	}
	
	private FigureType(String texturePath){
		this.texturePath = texturePath;
	}
}
